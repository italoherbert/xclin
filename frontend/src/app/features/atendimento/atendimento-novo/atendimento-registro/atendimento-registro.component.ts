import { Component, Input } from '@angular/core';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
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
    faCircleLeft : faCircleLeft
  }

  atendimentoSave : AtendimentoRegistro = {
    dataAtendimento : '',
    turno : '',
    valor : 0,
    retorno : false,
    paga : false,
    observacoes : '',
  }

  pacienteId : number = 0;
  especialidadeId : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  pacientesIDs : number[] = [];
  pacientesNomes : string[] = [];

  pacienteNome : string = '';
  buscandoPacientes : boolean = false;
  buscarPacientes : boolean = false;

  turnos : any[] = [];
  especialidades : Especialidade[] = [];

  senhaRepetida : any = '';

  constructor(
    private atendimentoService: AtendimentoService,
    private pacienteService: PacienteService, 
    private profissionalService: ProfissionalService,
    private sistemaService: SistemaService) {}

  recarrega( profissionalId : any ) {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    this.atendimentoService.getAtendimentoReg( profissionalId ).subscribe({
      next: (resp) => {
        this.turnos = resp.turnos;
        this.especialidades = resp.especialidades;
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
    
    this.showSpinner = true;

    this.atendimentoService.registraAtendimento( 
        this.clinicaId, this.profissionalId, this.especialidadeId, this.pacienteId, this.atendimentoSave ).subscribe({
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
          this.profissionalId, this.especialidadeId ).subscribe( {
      next: (resp) => {
        this.atendimentoSave.valor = resp.consultaValor;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  onValorAtendimentoAlterado( e : any ) {
    this.atendimentoSave.valor = e.valorReal;
  }

  pacienteOnSelect( pacienteId : number ) {
    this.pacienteId = pacienteId;
  }

  pacienteOnErroCreate( erro : any ) {
    this.erroMsg = this.sistemaService.mensagemErro( erro );
  }

}

