import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCheck, faCircleLeft, faEdit, faMoneyBill1, faMoneyBill1Wave, faPenToSquare, faPersonWalkingArrowLoopLeft, faRemove, faSackDollar, faWrench } from '@fortawesome/free-solid-svg-icons';

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
    faSackDollar : faSackDollar,
    faRemove: faRemove,
    faCheck: faCheck,
    faEdit: faEdit,
    faPersonEalkingArrowLoopLeft : faPersonWalkingArrowLoopLeft 
  }

  atendimento : Atendimento = {
    id : 0,
    observacoes: '',
    turno: '',
    dataAtendimento: '',
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
    orcamento: {
      pago: false,
      valorTotal: 0,
      valorPago: 0,
      temConsulta: false,
      consulta: {
        id: 0,
        especialidadeId: 0,
        especialidadeNome: '',
        valor: 0,
        concluida : false
      },
      exames: [],
      procedimentos: []
    } 
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

  onChangeConsultaConcluida( e : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let concluida = e.checked;
    let consultaId = this.atendimento.orcamento.consulta.id;    

    this.atendimentoService.alteraConsultaConcluida( consultaId, concluida ).subscribe({
      next: (resp) => {
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  onChangeExameItemConcluido( e : any, exameItemId : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let concluido = e.checked;

    this.atendimentoService.alteraExameItemConcluido( exameItemId, concluido ).subscribe({
      next: (resp) => {
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  onChangeProcItemConcluido( e : any, procItemId : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let concluido = e.checked;

    this.atendimentoService.alteraProcItemConcluido( procItemId, concluido ).subscribe({
      next: (resp) => {
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  verificaSeConcluido() {
    if ( this.atendimento.orcamento.consulta.concluida === false )
      return false;

    for( let i = 0; i < this.atendimento.orcamento.exames.length; i++ )
      if ( this.atendimento.orcamento.exames[ i ].concluido === false )
        return false;

    for( let i = 0; i < this.atendimento.orcamento.procedimentos.length; i++ )
      if ( this.atendimento.orcamento.procedimentos[ i ].concluido === false )
        return false;

    return true;
  }

}