import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCheck, faCircleLeft, faEdit, faMoneyBill1, faMoneyBill1Wave, faPenToSquare, faRemove, faWrench } from '@fortawesome/free-solid-svg-icons';

import * as moment from 'moment';

import { Consulta } from 'src/app/core/bean/consulta/consulta';
import { ConsultaService } from 'src/app/core/service/consulta.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

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
    faWrench : faWrench,
    faCircleLeft : faCircleLeft,
    faMoneyBill1 : faMoneyBill1,
    faRemove: faRemove,
    faCheck: faCheck,
    faEdit: faEdit
  }

  consulta : Consulta = {
    id : 0,
    observacoes: '',
    turno: '',
    dataAtendimento: '',
    paga: false,
    retorno: false,
    valor: 0,
    status: '',
    pacienteId: 0,
    pacienteNome: '',
    pacienteAnamnesePreenchida : false,
    profissionalId: 0,
    profissionalNome: '',
    clinicaId: 0,
    clinicaNome: '',
    especialidadeId: 0,
    especialidadeNome: '',
    statusLabel: '',
    turnoLabel: ''
  }

  constructor( 
    private actRoute : ActivatedRoute, 
    private consultaService: ConsultaService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.carrega();
  }
  
  carrega() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'consultaId' );

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

  realizarPagamento() {
    this.setaPagamento( true );
  }

  desfazerPagamento() {
    this.setaPagamento( false );
  }

  setaPagamento( paga : boolean ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    alert( paga );

    let id = this.actRoute.snapshot.paramMap.get( 'consultaId' );

    this.consultaService.setaPagamentoConsulta( id, paga ).subscribe({
      next: (resp) => {
        this.showSpinner = false;
        this.carrega();
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}