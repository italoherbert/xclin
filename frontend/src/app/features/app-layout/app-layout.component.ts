import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faAdd, faArrowLeft, faBedPulse, faBox, faCalendarCheck, faCalendarDays, faChevronDown, faChevronUp, faCircleUser, faCubes, faDisease, faDisplay, faFilePdf, faFilter, faHospital, faHospitalUser, faListOl, faMoneyBillTrendUp, faPeopleGroup, faPersonChalkboard, faPersonDotsFromLine, faStethoscope, faUmbrella, faUser, faUserDoctor, faUserGroup, faUserNurse, faUserTie, faUsersRays, faWrench } from '@fortawesome/free-solid-svg-icons';
import { SistemaService } from '../../core/service/sistema.service';
import { faSteam } from '@fortawesome/free-brands-svg-icons';

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
    faCalendarCheck : faCalendarCheck,
    faWrench : faWrench,
    faAdd : faAdd,
    faDisplay : faDisplay,
    faFilter : faFilter,
    faListOl : faListOl,
    faCubes : faCubes,
    faMoneyBillTrendUp : faMoneyBillTrendUp,
    faPeopleGroup : faPeopleGroup,
    faFilePdf : faFilePdf,
    faSteam : faSteam,
    faBedPulse : faBedPulse,
    faDisease : faDisease,
    faStethoscope : faStethoscope,
    faPersonDotsFromLing : faPersonDotsFromLine
  }

  usuarioMenuShow : boolean = false;
  atendimentoMenuShow : boolean = false;
  funcionarioMenuShow : boolean = false;
  exameMenuShow : boolean = false;
  relatorioMenuShow : boolean = false;

  constructor( private router : Router, public sistemaService: SistemaService ) {}

  logout() {
    localStorage.clear();
    this.router.navigate( [ '/login' ] );
  }
  
}
