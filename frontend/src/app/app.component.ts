import { SecurityService } from './app-security-module/security/security.service';
import { NavItem } from './app-security-module/models/nav-item';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Money Decision Making';

  isLoggedIn$: boolean = false;

    navList: NavItem[] = [
      { linkTitle: 'Administration', link: '/administration', icon: 'people', privileges: ['ROLE_ADMIN_PRIVILEGE'] },
      { linkTitle: 'Activity Log', link: '/activity-logs', icon: 'remove_red_eye', privileges: ['ROLE_ACTIVITY_PRIVILEGE'] },
      { linkTitle: 'Dashboard', link: '/dashboard', icon: 'dashboard' },
      { linkTitle: 'Decision', link: '/decisions', icon: 'ballot' }
    ];

    constructor(
      private securityService: SecurityService
    ) { }

    ngOnInit() {
      this.securityService.isLoggedIn.subscribe(logged => {
        this.isLoggedIn$ = logged;
      });
    }
}
