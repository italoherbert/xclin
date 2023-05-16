import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCircleLeft, faMoneyBill1, faMoneyBill1Wave, faPenToSquare, faRemove } from '@fortawesome/free-solid-svg-icons';

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
    faCircleLeft : faCircleLeft,
    faMoneyBill1 : faMoneyBill1,
    faRemove: faRemove
  }

  consulta : Consulta = {
    id : 0,
    observacoes: '',
    turno: '',
    dataAtendimento: '',
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
    private router : Router,
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
        this.consulta = resp;
        this.consulta.dataAtendimento = moment( resp.dataAtendimento, 'YYYY-MM-DD' ).format( 'DD/MM/YYYY' );
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}