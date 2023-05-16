import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faAdd, faBox, faCalendarDays, faChevronDown, faChevronUp, faCircleLeft, faCircleUser, faDisplay, faGear, faHospital, faHospitalUser, faHouseChimneyMedical, faSearch, faUser, faUserDoctor, faUserGroup, faUserNurse, faUserTie, faWrench } from '@fortawesome/free-solid-svg-icons';

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
    faHospital : faHospital,
    faChevronUp : faChevronUp,
    faChevronDown : faChevronDown,
    faCircleLeft : faCircleLeft,
    faUserNurse : faUserNurse,
    faUserDoctor : faUserDoctor,
    faUserTie : faUserTie,
    faHospitalUser : faHospitalUser,
    faCalendarDays : faCalendarDays,
    faWrench : faWrench,
    faAdd : faAdd,
    faDisplay : faDisplay
  }

  usuarioMenuShow : boolean = false;
  consultaMenuShow : boolean = false;

  constructor( private router : Router ) {}

  logout() {
    localStorage.clear();
    this.router.navigate( [ '/login' ] );
  }

}
