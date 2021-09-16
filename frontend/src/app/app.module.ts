import { ActivityLogComponent } from './activity-log/activity-log.component';
import { HttpInterceptorModule } from './app-security-module/security/http-interceptor.module';
import { MaterialModule } from './app-security-module/shared/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { AppSecurityModule } from './app-security-module/app-security.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DecisionsComponent } from './decisions/decisions.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import {NgApexchartsModule} from 'ng-apexcharts';
import { TradingChartComponent } from './generics/chart/trading-chart.component';
import { ExpandGraphDialogComponent } from './dashboard/show-dialog/expand-graph-dialog/expand-graph-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    ActivityLogComponent,
    DashboardComponent,
    DecisionsComponent,
    TradingChartComponent,
    ExpandGraphDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    CommonModule,
    HttpClientModule,
    // FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    HttpInterceptorModule,
    AppSecurityModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    NgApexchartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
