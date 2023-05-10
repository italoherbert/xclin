import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faBox, faChevronDown, faChevronUp, faCircleLeft, faCircleUser, faGear, faHouseChimneyMedical, faUser, faUserGroup, faUserNurse } from '@fortawesome/free-solid-svg-icons';

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
    faGear : faGear,
    faHouseChimneyMedical : faHouseChimneyMedical,
    faChevronUp : faChevronUp,
    faChevronDown : faChevronDown,
    faCircleLeft : faCircleLeft,
    faUserNurse : faUserNurse    
  }

  usuarioMenuShow : boolean = false;

  constructor( private router : Router ) {}

  logout() {
    localStorage.clear();
    this.router.navigate( [ '/login' ] );
  }

}
