import {Injectable, NgModule} from '@angular/core';
import {
  HTTP_INTERCEPTORS,
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {SecurityService} from './security.service';
import {Router} from '@angular/router';
import {catchError, map} from 'rxjs/operators';
import {DialogService} from '../shared/common-dialogs/dialog.service';
import {ErrorDto, ValidationErrorDto} from '../models/error';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private securityService: SecurityService,
    private dialogService: DialogService
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    const token = localStorage.getItem('bearerToken');

    if (token) {
      req = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + token)
      });
    }

    return next.handle(req).pipe(
      map((event: HttpEvent<any>) => {
        // if (event instanceof HttpResponse) {
        // console.log('event--->>>', event);
        // this.errorDialogService.openDialog(event);
        // }
        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        let data = {};
        data = {
          reason: error && error.error.reason ? error.error.reason : '',
          status: error.status
        };
        if (error.status === 401 || error.status === 403) {
          this.securityService.logoutProcess();
          const returnUrl = this.router.url.indexOf('login') > -1 ? '' : `?returnUrl=${this.router.url}`;
          this.router.navigateByUrl(`/login${returnUrl}`);
        } else {
          let messages = [];
          if (error.error.errorCode) {
            const errorDto: ErrorDto = error.error;
            if (errorDto.errorCode === 'DATA_VALIDATION') {
              messages = error.error.errors.map((ve: ValidationErrorDto) => ve.errorCode);
            } else {
              messages = [errorDto.errorCode];
            }
          } else {
            messages = [error.message];
          }
          this.dialogService.openAlertDialog({
            messages: messages
          });
        }
        return throwError(error);
      })
    );

  }
}

@NgModule({
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true,
      deps: [Router, SecurityService, DialogService]
    }
  ]
})
export class HttpInterceptorModule {
}
