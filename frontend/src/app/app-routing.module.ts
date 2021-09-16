import { DashboardComponent } from './dashboard/dashboard.component';
import { DecisionsComponent } from './decisions/decisions.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivityLogComponent } from './activity-log/activity-log.component';
import { AuthGuard } from './app-security-module/security/auth.guard';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard],
  data: {privileges: ['ROLE_VIEWER_PRIVILEGE', 'ROLE_EXECUTOR_PRIVILEGE']} },
  { path: 'decisions', component: DecisionsComponent, canActivate: [AuthGuard],
  data: {privileges: ['ROLE_VIEWER_PRIVILEGE', 'ROLE_EXECUTOR_PRIVILEGE']} },
  { path: 'activity-logs', component: ActivityLogComponent, canActivate: [AuthGuard],
   data: {privileges: ['ROLE_ACTIVITY_PRIVILEGE']} },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
