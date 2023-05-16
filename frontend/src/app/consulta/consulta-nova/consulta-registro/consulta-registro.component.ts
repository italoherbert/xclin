import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { ConsultaRegistro } from 'src/app/bean/consulta/consulta-registro';
import { ConsultaService } from 'src/app/service/consulta.service';
import { SistemaService } from 'src/app/service/sistema.service';

import * as moment from 'moment';
import { PacienteService } from 'src/app/service/paciente.service';

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
    tempoEstimado : 0,
    turno : '',
    valor : 0,
    retorno : false,
    paga : false,
    observacoes : '',
  }

  pacienteId : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  pacientesIDs : number[] = [];
  pacientesNomes : string[] = [];

  pacienteNome : string = '';
  buscandoPacientes : boolean = false;

  turnos : any[] = [];

  senhaRepetida : any = '';

  constructor(
    private router : Router,
    private actRoute : ActivatedRoute, 
    private consultaService: ConsultaService,
    private pacienteService: PacienteService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    this.consultaService.getConsultaReg().subscribe({
      next: (resp) => {
        this.turnos = resp.turnos;
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

    this.consultaService.registraConsulta( this.clinicaId, this.profissionalId, this.pacienteId, this.consultaSave ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Consulta registrado com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

  onPacienteInput( event : any ) {
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

}

