import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';

import * as moment from 'moment';
import { ConsultaRemarcarSave } from 'src/app/bean/consulta/consulta-remarcar-save';
import { ConsultaService } from 'src/app/service/consulta.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-consulta-remarcar-save',
  templateUrl: './consulta-remarcar-save.component.html',
  styleUrls: ['./consulta-remarcar-save.component.css']
})
export class ConsultaRemarcarSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  consultaSave : ConsultaRemarcarSave = {
    dataAtendimento : '',
    turno : ''
  }

  pacienteId : number = 0;

  turnos : any[] = [];

  constructor(
    private actRoute : ActivatedRoute, 
    private consultaService: ConsultaService,
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    this.consultaService.getConsultaReg().subscribe({
      next: (resp) => {
        this.turnos = resp.turnos;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });   
  }

  remarca() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    let consultaId = this.actRoute.snapshot.paramMap.get( 'consultaId' );

    let dia = this.actRoute.snapshot.paramMap.get( 'dia' );
    let mes = this.actRoute.snapshot.paramMap.get( 'mes' );
    let ano = this.actRoute.snapshot.paramMap.get( 'ano' );

    let d = ''+ano+'-'+mes+'-'+dia;

    this.consultaSave.dataAtendimento = moment( d, 'YYYY-MM-DD' ).format( 'YYYY-MM-DD' );

    this.showSpinner = true;

    this.consultaService.remarcaConsulta( consultaId, this.consultaSave ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Consulta remarcada com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

}