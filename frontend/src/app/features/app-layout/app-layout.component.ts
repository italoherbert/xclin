import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faAdd, faArrowLeft, faBox, faCalendarDays, faChevronDown, faChevronUp, faCircleUser, faDisplay, faFilter, faHospital, faHospitalUser, faListOl, faUmbrella, faUser, faUserDoctor, faUserGroup, faUserNurse, faUserTie, faWrench } from '@fortawesome/free-solid-svg-icons';
import { SistemaService } from '../../core/service/sistema.service';

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
    faHospital : faHospital,
    faChevronUp : faChevronUp,
    faChevronDown : faChevronDown,
    faArrowLeft : faArrowLeft,
    faUserNurse : faUserNurse,
    faUserDoctor : faUserDoctor,
    faUserTie : faUserTie,
    faHospitalUser : faHospitalUser,
    faCalendarDays : faCalendarDays,
    faWrench : faWrench,
    faAdd : faAdd,
    faDisplay : faDisplay,
    faFilter : faFilter,
    faListOl : faListOl,
    faUmbrella : faUmbrella
  }

  usuarioMenuShow : boolean = false;
  consultaMenuShow : boolean = false;

  constructor( private router : Router, public sistemaService: SistemaService ) {}

  logout() {
    localStorage.clear();
    this.router.navigate( [ '/login' ] );
  }
  
}
