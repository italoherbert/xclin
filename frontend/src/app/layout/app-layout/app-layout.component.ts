import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faBox, faCircleUser, faGear, faUser, faUserGroup } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.css']
})
export class AppLayoutComponent {

  icons : any = {
    faUser : faUser,
    faUserGroup : faUserGroup,
    faBox : faBox,
    faCircleUser : faCircleUser,
    faGear : faGear
  }

  constructor( private router : Router ) {}

  logout() {
    localStorage.clear();
    this.router.navigate( [ '/login' ] );
  }

}
