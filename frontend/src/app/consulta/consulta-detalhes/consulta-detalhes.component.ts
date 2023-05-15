import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';

import * as moment from 'moment';

import { Consulta } from 'src/app/bean/consulta/consulta';
import { ConsultaService } from 'src/app/service/consulta.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-consulta-detalhes',
  templateUrl: './consulta-detalhes.component.html',
  styleUrls: ['./consulta-detalhes.component.css']
})
export class ConsultaDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  consultaDetalhes : Consulta = {
    id : 0,
    observacoes: '',
    turno: '',
    dataHoraAgendamento: '',
    paga: false,
    retorno: false,
    tempoEstimado: 0,
    valor: 0,
    status: '',
    pacienteId: 0,
    pacienteNome: '',
    clinicaId: 0,
    clinicaNome: '',
    statusLabel: '',
    turnoLabel: ''
  }

  constructor( 
    private actRoute : ActivatedRoute, 
    private consultaService: ConsultaService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.consultaService.getConsulta( id ).subscribe({
      next: ( resp ) => {
        this.consultaDetalhes = resp;
        this.consultaDetalhes.dataHoraAgendamento = moment( resp.dataHoraAgendamento ).format( 'DD/MM/YYYY HH:mm' );
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}