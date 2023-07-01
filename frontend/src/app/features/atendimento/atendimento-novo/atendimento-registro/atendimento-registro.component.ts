import { Component, Input } from '@angular/core';
import { faCircleLeft, faPlus, faRemove, faSave, faX } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoRegistro } from 'src/app/core/bean/atendimento/atendimento-registro';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

import * as moment from 'moment';
import { Especialidade } from 'src/app/core/bean/especialidade/especialidade';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { PacienteService } from 'src/app/core/service/paciente.service';

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
    pago : false,
    valorPago : 0,
    temConsulta : false,
    consulta : {
      especialidadeId : 0,
      retorno : false,
      valor : 0
    },
    exames : []
  }

  exameIncluidoSelecionadoI : number = -1;
  exameNaoIncluidoSelecionadoI : number = -1;

  exameValorAtual : number = 0;
  
  examesIncluidosIDs : number[] = [];
  examesIncluidosNomes : string[] = [];
  examesIncluidosValores : number[] = [];

  examesNaoIncluidosIDs : number[] = [];
  examesNaoIncluidosNomes : string[] = [];

  valorTotal : number = 0;

  pacienteId : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  pacientesIDs : number[] = [];
  pacientesNomes : string[] = [];

  turnos : any[] = [];
  especialidades : Especialidade[] = [];

  senhaRepetida : any = '';

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

        this.examesNaoIncluidosIDs.splice( 0, this.examesIncluidosIDs.length );
        this.examesNaoIncluidosNomes.splice( 0, this.examesIncluidosNomes.length );

        let exames = resp.profissionalExames;
        for( let i = 0; i < exames.length; i++ ) {
          this.examesNaoIncluidosIDs.push( exames[ i ].id );
          this.examesNaoIncluidosNomes.push( exames[ i ].nome );
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

    this.atendimentoSave.exames.splice( 0, this.atendimentoSave.exames.length );

    for( let i = 0; i < this.examesIncluidosIDs.length; i++ ) {
      this.atendimentoSave.exames.push( {
        exameId : this.examesIncluidosIDs[ i ],
        valor : this.examesIncluidosValores[ i ]
      } );
    }
    
    this.showSpinner = true;

    this.atendimentoService.registraAtendimento( 
        this.clinicaId, this.profissionalId, this.pacienteId, this.atendimentoSave ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Atendimento registrada com sucesso.";
        this.showSpinner = false;
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
          this.profissionalId, this.atendimentoSave.consulta.especialidadeId ).subscribe( {
      next: (resp) => {
        this.atendimentoSave.consulta.valor = resp.consultaValor;
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
    this.examesIncluidosIDs.push( this.examesNaoIncluidosIDs[ i ] );
    this.examesIncluidosNomes.push( this.examesNaoIncluidosNomes[ i ] );
    this.examesIncluidosValores.push( 0 );

    this.examesNaoIncluidosIDs.splice( i, 1 );
    this.examesNaoIncluidosNomes.splice( i, 1 );    

    this.exameNaoIncluidoSelecionadoI = -1;
  }

  removeExame() {
    let i = this.exameIncluidoSelecionadoI;

    this.examesNaoIncluidosIDs.push( this.examesIncluidosIDs[ i ] );
    this.examesNaoIncluidosNomes.push( this.examesIncluidosNomes[ i ] );

    this.examesIncluidosIDs.splice( i, 1 );
    this.examesIncluidosNomes.splice( i, 1 );
    this.examesIncluidosValores.splice( i, 1 );

    this.exameIncluidoSelecionadoI = -1;
  }

  novoExameValor() {
    let i = this.exameIncluidoSelecionadoI;
    if ( i < this.examesIncluidosValores.length )
      this.examesIncluidosValores[ i ] = this.exameValorAtual;  

    this.atualizaValorTotal();
  }
   
  atualizaValorTotal(): void {
    this.valorTotal = 0;
    if ( this.atendimentoSave.temConsulta === true )
      this.valorTotal += this.atendimentoSave.consulta.valor;

    for( let i = 0; i < this.examesIncluidosValores.length; i++ )
      this.valorTotal += this.examesIncluidosValores[ i ];
  }

  onValorConsultaAlterado( e : any ) {
    this.atendimentoSave.consulta.valor = e.valorReal;
    this.atualizaValorTotal();
  }

  onValorExameAlterado( e : any ) {
    this.exameValorAtual = e.valorReal;    
  }

  onValorPagoAlterado( e : any ) {
    this.atendimentoSave.valorPago = e.valorReal;
  }

  pacienteOnSelect( pacienteId : number ) {
    this.pacienteId = pacienteId;
  }

  pacienteOnErroCreate( erro : any ) {
    this.erroMsg = this.sistemaService.mensagemErro( erro );
  }

}

