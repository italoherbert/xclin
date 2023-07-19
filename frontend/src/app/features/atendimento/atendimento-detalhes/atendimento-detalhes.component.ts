import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCheck, faCircleLeft, faEdit, faMoneyBill1, faMoneyBill1Wave, faPenToSquare, faRemove, faWrench } from '@fortawesome/free-solid-svg-icons';

import * as moment from 'moment';

import { Atendimento } from 'src/app/core/bean/atendimento/atendimento';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-atendimento-detalhes',
  templateUrl: './atendimento-detalhes.component.html',
  styleUrls: ['./atendimento-detalhes.component.css']
})
export class AtendimentoDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faWrench : faWrench,
    faCircleLeft : faCircleLeft,
    faMoneyBill1 : faMoneyBill1,
    faRemove: faRemove,
    faCheck: faCheck,
    faEdit: faEdit
  }

  atendimento : Atendimento = {
    id : 0,
    observacoes: '',
    turno: '',
    dataAtendimento: '',
    pago: false,
    valorTotal: 0,
    valorPago: 0,
    status: '',
    pacienteId: 0,
    pacienteNome: '',
    pacienteAnamneseCriada : false,
    profissionalId: 0,
    profissionalNome: '',
    clinicaId: 0,
    clinicaNome: '',
    statusLabel: '',
    turnoLabel: '',
    temConsulta: false,
    consulta: {
      id: 0,
      especialidadeId: 0,
      especialidadeNome: '',
      valor: 0
    },
    exames: [],
    procedimentos: []
  }

  constructor( 
    private actRoute : ActivatedRoute, 
    private atendimentoService: AtendimentoService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.carrega();
  }
  
  carrega() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let atendimentoId = this.actRoute.snapshot.paramMap.get( 'atendimentoId' );

    this.atendimentoService.getAtendimento( atendimentoId ).subscribe({
      next: ( resp ) => {
        this.atendimento = resp;
        this.atendimento.dataAtendimento = moment( resp.dataAtendimento, 'YYYY-MM-DD' ).format( 'DD/MM/YYYY' );
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}