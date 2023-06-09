import { Component, Input } from '@angular/core';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { ConsultaRegistro } from 'src/app/core/bean/consulta/consulta-registro';
import { ConsultaService } from 'src/app/core/service/consulta.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

import * as moment from 'moment';
import { Especialidade } from 'src/app/core/bean/especialidade/especialidade';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { PacienteService } from 'src/app/core/service/paciente.service';

@Component({
  selector: 'app-consulta-registro',
  templateUrl: './consulta-registro.component.html',
  styleUrls: ['./consulta-registro.component.css']
})
export class ConsultaRegistroComponent {

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

  consultaSave : ConsultaRegistro = {
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

  turnos : any[] = [];
  especialidades : Especialidade[] = [];

  senhaRepetida : any = '';

  constructor(
    private consultaService: ConsultaService,
    private pacienteService: PacienteService, 
    private profissionalService: ProfissionalService,
    private sistemaService: SistemaService) {}

  recarrega( profissionalId : any ) {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    this.consultaService.getConsultaReg( profissionalId ).subscribe({
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

    this.consultaSave.dataAtendimento = moment( d ).format( 'YYYY-MM-DD' );

    if( this.turno > 0 )
      this.consultaSave.turno = this.turnos[ this.turno-1 ].name;
    
    this.showSpinner = true;

    this.consultaService.registraConsulta( 
        this.clinicaId, this.profissionalId, this.especialidadeId, this.pacienteId, this.consultaSave ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Consulta registrada com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

  onPacienteInput( event : any ) {
    if ( this.pacienteNome.length == 0 )
      return;    

    if ( this.buscandoPacientes === false ) { 
      this.buscandoPacientes = true;

      this.pacienteService.listaPacientesLimite( this.clinicaId, this.pacienteNome, 4 ).subscribe( {
        next: (resp) => {
          this.pacientesIDs = resp.ids;
          this.pacientesNomes = resp.nomes;
          this.buscandoPacientes = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.buscandoPacientes = false;
        }
      } );
    }
  }

  onPacienteSelected( event : any ) {
    this.pacienteId = this.pacientesIDs[ event.option.id ];
  }

  onEspecialidadeSelected( event : any ) {
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.profissionalService.getProfissionalEspecialidadeVinculo( 
          this.profissionalId, this.especialidadeId ).subscribe( {
      next: (resp) => {
        this.consultaSave.valor = resp.consultaValor;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  onValorConsultaAlterado( e : any ) {
    this.consultaSave.valor = e.valorReal;
  }

}

