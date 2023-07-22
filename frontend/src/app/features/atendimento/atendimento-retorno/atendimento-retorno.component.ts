import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoRetornoSave } from 'src/app/core/bean/atendimento/atendimento-retorno-save';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

import * as moment from 'moment';

@Component({
  selector: 'app-atendimento-retorno',
  templateUrl: './atendimento-retorno.component.html',
  styleUrls: ['./atendimento-retorno.component.css']
})
export class AtendimentoRetornoComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave
  }

  dia : number = 0;
  mes : number = 0;
  ano : number = 0;
  turno : number = 0;

  quantidadesAgrupadasPorDia : any[][] = [];

  turnos : any[] = [];
  
  atendimentoRetornoSave : AtendimentoRetornoSave = {
    dataAtendimento : '',
    turno : ''
  }

  constructor(
    private actRoute: ActivatedRoute,
    private atendimentoService: AtendimentoService,
    private sistemaService: SistemaService
  ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    this.atendimentoService.getAtendimentoRetorno().subscribe({
      next: (resp) => {
        this.turnos = resp.turnos;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    })
  }

  remarca() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    let atendimentoId = this.actRoute.snapshot.paramMap.get( 'atendimentoId' );

    let d = ''+this.ano+'-'+this.mes+'-'+this.dia;

    this.atendimentoRetornoSave.dataAtendimento = moment( d, 'YYYY-MM-DD' ).format();

    if ( this.turno < 1 ) {
      this.erroMsg = "Selecione um dia e turno no calendário.";
      return;
    }

    this.atendimentoRetornoSave.turno = this.turnos[ this.turno-1 ].name;

    this.showSpinner = true;

    this.atendimentoService.registraRetorno( atendimentoId, this.atendimentoRetornoSave ).subscribe({
      next: ( resp ) => {
        this.onCalendarioAlterado( { mes : this.mes, ano : this.ano } );
        this.infoMsg = "Retorno registrado com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });      
  }

  onCalendarioAlterado( event : any ) {
    this.mes = event.mes;
    this.ano = event.ano;

    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'atendimentoId' );

    this.atendimentoService.getQuantidadesAgrupadasAtendimentoID( id, this.mes, this.ano ).subscribe( {
        next: (resp ) => {
          this.quantidadesAgrupadasPorDia = resp;
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
    } );

  }

  onDiaTurnoAlterado( event : any ) {
    this.dia = event.dia;
    this.mes = event.mes;
    this.ano = event.ano;
    this.turno = event.turno;
  }

}
