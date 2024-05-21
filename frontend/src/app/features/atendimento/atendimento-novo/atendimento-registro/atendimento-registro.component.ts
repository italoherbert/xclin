import { Component, Input, Output, ViewChild, EventEmitter } from '@angular/core';
import { faCircleLeft, faPlus, faRemove, faSave, faX } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoRegistro } from 'src/app/core/bean/atendimento/atendimento-registro';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

import * as moment from 'moment';
import { Especialidade } from 'src/app/core/bean/especialidade/especialidade';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { RealInputDirective } from 'src/app/shared/directive/real-input/real-input.directive';
import { PacienteAutocompleteInputComponent } from 'src/app/shared/paciente-autocomplete-input/paciente-autocomplete-input.component';

@Component({
  selector: 'app-atendimento-registro',
  templateUrl: './atendimento-registro.component.html',
  styleUrls: ['./atendimento-registro.component.css']
})
export class AtendimentoRegistroComponent {

  @Input() clinicaId: number = 0;
  @Input() profissionalId: number = 0;
  @Input() dia : number = 0;
  @Input() mes : number = 0;
  @Input() ano : number = 0;
  @Input() turno : number = 0;

  @Output() onRegistrado : EventEmitter<any> = new EventEmitter<any>();

  @ViewChild( 'consultaValor', {read : RealInputDirective} ) consultaValorDirective! : RealInputDirective;
  @ViewChild( 'pacienteAutocompleteInput', {read: PacienteAutocompleteInputComponent}) pacienteAutocompleteInput! : PacienteAutocompleteInputComponent;

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft,
    faPlus : faPlus,
    faRemove : faRemove
  }

  atendimentoSave : AtendimentoRegistro = {
    dataAtendimento : '',
    turno : '',   
    observacoes : '',    
    orcamento: {
      pago: false,
      valorTotal: 0,
      valorPago: 0,
      temConsulta: false,
      consulta: {
        especialidadeId: 0,
        valor: 0
      },
      exames: [],
      procedimentos: []
    } 
  }

  exameIncluidoSelecionadoI : number = -1;
  exameNaoIncluidoSelecionadoI : number = -1;

  exameValorAtual : number = 0;
  
  examesIncluidosIDs : number[] = [];
  examesIncluidosNomes : string[] = [];
  examesIncluidosValores : number[] = [];

  examesNaoIncluidosIDs : number[] = [];
  examesNaoIncluidosNomes : string[] = [];
  examesNaoIncluidosValores : number[] = [];

  procedimentoIncluidoSelecionadoI : number = -1;
  procedimentoNaoIncluidoSelecionadoI : number = -1;

  procedimentoValorAtual : number = 0;
  
  procedimentosIncluidosIDs : number[] = [];
  procedimentosIncluidosNomes : string[] = [];
  procedimentosIncluidosValores : number[] = [];

  procedimentosNaoIncluidosIDs : number[] = [];
  procedimentosNaoIncluidosNomes : string[] = [];
  procedimentosNaoIncluidosValores : number[] = [];

  valorTotal : number = 0;

  pacienteId : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  pacientesIDs : number[] = [];
  pacientesNomes : string[] = [];

  turnos : any[] = [];
  especialidades : Especialidade[] = [];

  constructor(
    private atendimentoService: AtendimentoService,
    private profissionalService: ProfissionalService,
    private sistemaService: SistemaService) {}
  
  recarrega() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    this.atendimentoService.getAtendimentoReg( this.profissionalId ).subscribe({
      next: (resp) => {
        this.turnos = resp.turnos;
        this.especialidades = resp.especialidades;

        this.examesNaoIncluidosIDs.splice( 0, this.examesNaoIncluidosIDs.length );
        this.examesNaoIncluidosNomes.splice( 0, this.examesNaoIncluidosNomes.length );
        this.examesNaoIncluidosValores.splice( 0, this.examesNaoIncluidosValores.length );

        this.examesIncluidosIDs.splice( 0, this.examesIncluidosIDs.length );
        this.examesIncluidosNomes.splice( 0, this.examesIncluidosNomes.length );
        this.examesIncluidosValores.splice( 0, this.examesIncluidosValores.length );

        this.procedimentosNaoIncluidosIDs.splice( 0, this.procedimentosNaoIncluidosIDs.length );
        this.procedimentosNaoIncluidosNomes.splice( 0, this.procedimentosNaoIncluidosNomes.length );
        this.procedimentosNaoIncluidosValores.splice( 0, this.procedimentosNaoIncluidosValores.length );

        this.procedimentosIncluidosIDs.splice( 0, this.procedimentosIncluidosIDs.length );
        this.procedimentosIncluidosNomes.splice( 0, this.procedimentosIncluidosNomes.length );
        this.procedimentosIncluidosValores.splice( 0, this.procedimentosIncluidosValores.length );

        let exames = resp.profissionalExames;
        for( let i = 0; i < exames.length; i++ ) {
          this.examesNaoIncluidosIDs.push( exames[ i ].exameId );
          this.examesNaoIncluidosNomes.push( exames[ i ].exameNome );
          this.examesNaoIncluidosValores.push( exames[ i ].exameValor );
        }

        let procedimentos = resp.profissionalProcedimentos;
        for( let i = 0; i < procedimentos.length; i++ ) {
          this.procedimentosNaoIncluidosIDs.push( procedimentos[ i ].procedimentoId );
          this.procedimentosNaoIncluidosNomes.push( procedimentos[ i ].procedimentoNome );
          this.procedimentosNaoIncluidosValores.push( procedimentos[ i ].procedimentoValor );
        }
        
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });   
  }

  registra() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    let d = ''+this.ano+'-'+this.mes+'-'+this.dia;

    this.atendimentoSave.dataAtendimento = moment( d ).format( 'YYYY-MM-DD' );

    if( this.turno > 0 )
      this.atendimentoSave.turno = this.turnos[ this.turno-1 ].name;

    this.atendimentoSave.orcamento.exames.splice( 0, this.atendimentoSave.orcamento.exames.length );
    this.atendimentoSave.orcamento.procedimentos.splice( 0, this.atendimentoSave.orcamento.procedimentos.length );

    for( let i = 0; i < this.examesIncluidosIDs.length; i++ ) {
      this.atendimentoSave.orcamento.exames.push( {
        exameId : this.examesIncluidosIDs[ i ],
        valor : this.examesIncluidosValores[ i ]
      } );
    }

    for( let i = 0; i < this.procedimentosIncluidosIDs.length; i++ ) {
      this.atendimentoSave.orcamento.procedimentos.push( {
        procedimentoId : this.procedimentosIncluidosIDs[ i ],
        valor : this.procedimentosIncluidosValores[ i ]
      } );
    }
    
    this.showSpinner = true;

    this.atendimentoService.registraAtendimento( 
        this.clinicaId, this.profissionalId, this.pacienteId, this.atendimentoSave ).subscribe({
      next: ( resp ) => {
        this.showSpinner = false;

        this.onRegistrado.emit();
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }
  
  onEspecialidadeSelected( event : any ) {
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.profissionalService.getProfissionalEspecialidadeVinculo( 
          this.profissionalId, this.atendimentoSave.orcamento.consulta.especialidadeId ).subscribe( {
      next: (resp) => {
        this.atendimentoSave.orcamento.consulta.valor = resp.consultaValor;
        this.atualizaValorTotal();

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  adicionaExame() { 
    let i = this.exameNaoIncluidoSelecionadoI;  
    
    if ( i !== -1 ) {
      this.examesIncluidosIDs.push( this.examesNaoIncluidosIDs[ i ] );
      this.examesIncluidosNomes.push( this.examesNaoIncluidosNomes[ i ] );
      this.examesIncluidosValores.push( this.examesNaoIncluidosValores[ i ] );

      this.examesNaoIncluidosIDs.splice( i, 1 );
      this.examesNaoIncluidosNomes.splice( i, 1 );    
      this.examesNaoIncluidosValores.splice( i, 1 );

      this.atualizaValorTotal();

      this.exameIncluidoSelecionadoI = this.examesIncluidosIDs.length-1;
      this.exameNaoIncluidoSelecionadoI = -1;
    }
  }

  removeExame() {
    let i = this.exameIncluidoSelecionadoI;
    if ( i !== -1 ) {
      this.examesNaoIncluidosIDs.push( this.examesIncluidosIDs[ i ] );
      this.examesNaoIncluidosNomes.push( this.examesIncluidosNomes[ i ] );
      this.examesNaoIncluidosValores.push( this.examesIncluidosValores[ i ] );

      this.examesIncluidosIDs.splice( i, 1 );
      this.examesIncluidosNomes.splice( i, 1 );
      this.examesIncluidosValores.splice( i, 1 );

      this.exameNaoIncluidoSelecionadoI = this.examesNaoIncluidosIDs.length-1;
      this.exameIncluidoSelecionadoI = -1;
    }
  }

  novoExameValor() {
    let i = this.exameIncluidoSelecionadoI;
    if ( i < this.examesIncluidosValores.length )
      this.examesIncluidosValores[ i ] = this.exameValorAtual;  

    this.atualizaValorTotal();
  }

  adicionaProcedimento() { 
    let i = this.procedimentoNaoIncluidoSelecionadoI;  
    
    if ( i !== -1 ) {
      this.procedimentosIncluidosIDs.push( this.procedimentosNaoIncluidosIDs[ i ] );
      this.procedimentosIncluidosNomes.push( this.procedimentosNaoIncluidosNomes[ i ] );
      this.procedimentosIncluidosValores.push( this.procedimentosNaoIncluidosValores[ i ] );

      this.procedimentosNaoIncluidosIDs.splice( i, 1 );
      this.procedimentosNaoIncluidosNomes.splice( i, 1 );    
      this.procedimentosNaoIncluidosValores.splice( i, 1 );

      this.atualizaValorTotal();

      this.procedimentoIncluidoSelecionadoI = this.procedimentosIncluidosIDs.length-1;
      this.procedimentoNaoIncluidoSelecionadoI = -1;
    }
  }

  removeProcedimento() {
    let i = this.procedimentoIncluidoSelecionadoI;
    if ( i !== -1 ) {
      this.procedimentosNaoIncluidosIDs.push( this.procedimentosIncluidosIDs[ i ] );
      this.procedimentosNaoIncluidosNomes.push( this.procedimentosIncluidosNomes[ i ] );
      this.procedimentosNaoIncluidosValores.push( this.procedimentosIncluidosValores[ i ] );

      this.procedimentosIncluidosIDs.splice( i, 1 );
      this.procedimentosIncluidosNomes.splice( i, 1 );
      this.procedimentosIncluidosValores.splice( i, 1 );

      this.procedimentoNaoIncluidoSelecionadoI = this.procedimentosNaoIncluidosIDs.length-1;
      this.procedimentoIncluidoSelecionadoI = -1;
    }
  }

  novoProcedimentoValor() {
    let i = this.procedimentoIncluidoSelecionadoI;
    if ( i < this.procedimentosIncluidosValores.length )
      this.procedimentosIncluidosValores[ i ] = this.procedimentoValorAtual;  

    this.atualizaValorTotal();
  }

  atualizaValorTotal(): void {
    this.valorTotal = 0;
    if ( this.atendimentoSave.orcamento.temConsulta === true )
      this.valorTotal += this.atendimentoSave.orcamento.consulta.valor;

    for( let i = 0; i < this.examesIncluidosValores.length; i++ )
      this.valorTotal += this.examesIncluidosValores[ i ];

    for( let i = 0; i < this.procedimentosIncluidosValores.length; i++ )
      this.valorTotal += this.procedimentosIncluidosValores[ i ];
  }

  onValorConsultaAlterado( e : any ) {
    this.atendimentoSave.orcamento.consulta.valor = e.valorReal;
    this.atualizaValorTotal();
  }

  onValorExameAlterado( e : any ) {
    this.exameValorAtual = e.valorReal;    
  }

  onValorProcedimentoAlterado( e : any ) {
    this.procedimentoValorAtual = e.valorReal;
  }

  onValorTotalAlterado( e : any ) {
    this.atendimentoSave.orcamento.valorTotal = e.valorReal;
  }

  onValorPagoAlterado( e : any ) {
    this.atendimentoSave.orcamento.valorPago = e.valorReal;
  }

  pacienteOnSelect( pacienteId : number ) {
    this.pacienteId = pacienteId;
  }

  pacienteOnErroCreate( erro : any ) {
    this.erroMsg = this.sistemaService.mensagemErro( erro );
  }

  limpaForm() {
    this.atendimentoSave = {
      dataAtendimento : '',
      turno : '',   
      observacoes : '',    
      orcamento: {
        pago: false,
        valorTotal: 0,
        valorPago: 0,
        temConsulta: false,
        consulta: {
          especialidadeId: 0,
          valor: 0
        },
        exames: [],
        procedimentos: []
      }
    } 

    this.pacienteId = 0;
    this.exameValorAtual = 0;
    this.valorTotal = 0;
    
    this.examesIncluidosIDs.splice( 0, this.examesIncluidosIDs.length );
    this.examesIncluidosNomes.splice( 0, this.examesIncluidosNomes.length );
    this.examesIncluidosValores.splice( 0, this.examesIncluidosValores.length );

    this.pacienteAutocompleteInput.limpa();
  }

}

