import {Component, Input, OnInit} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {NavItem} from '../models/nav-item';
import {SecurityService} from '../security/security.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  activeLink = '';
  @Input() title: string = '';
  @Input() navList: NavItem[] = [];

  loggedUser: string = '';

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(
    private breakpointObserver: BreakpointObserver,
    private securityService: SecurityService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.securityService.loggedUserIn.subscribe(username => {
      this.loggedUser = username;
    });
    this.navList = this.navList.filter(item => this.securityService.hasPrivilege(item.privileges));
  }

  handleClick(selectedItem: NavItem) {
    this.activeLink = selectedItem.linkTitle;
  }

  logout() {
    this.securityService.logout();
    this.router.navigate(['/login']);
  }
}
