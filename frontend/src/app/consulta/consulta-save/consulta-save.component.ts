import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { ConsultaSave } from 'src/app/bean/consulta/consulta-save';
import { ConsultaService } from 'src/app/service/consulta.service';
import { SistemaService } from 'src/app/service/sistema.service';

import * as moment from 'moment';
import { PacienteService } from 'src/app/service/paciente.service';

@Component({
  selector: 'app-consulta-save',
  templateUrl: './consulta-save.component.html',
  styleUrls: ['./consulta-save.component.css']
})
export class ConsultaSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  consultaSave : ConsultaSave = {
    dataHoraAgendamento : '',
    tempoEstimado : 0,
    turno : '',
    valor : 0,
    retorno : false,
    paga : false,
    observacoes : '',
  }

  pacienteId : number = 0;

  dataAgendamento : string = '';
  horaAgendamento : string = ''; 

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

    if ( /\d\d:\d\d/.test( this.horaAgendamento ) == false ) {
      this.erroMsg = "Hora da consulta em formato inválido.";
      return;
    }
    
    let clinicaId = this.actRoute.snapshot.paramMap.get( 'clinicaId' );
    let profissionalId = this.actRoute.snapshot.paramMap.get( 'profissionalId' );

    let dia = this.actRoute.snapshot.paramMap.get( 'dia' );
    let mes = this.actRoute.snapshot.paramMap.get( 'mes' );
    let ano = this.actRoute.snapshot.paramMap.get( 'ano' );

    let d = ''+ano+'-'+mes+'-'+dia+' '+this.horaAgendamento;

    this.consultaSave.dataHoraAgendamento = moment( d ).format( 'YYYY-MM-DD HH:mm:ss' );

    this.showSpinner = true;

    this.consultaService.registraConsulta( clinicaId, profissionalId, this.pacienteId, this.consultaSave ).subscribe({
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
      let clinicaId = this.actRoute.snapshot.paramMap.get( 'clinicaId' );

      this.buscandoPacientes = true;

      this.pacienteService.listaPacientesLimite( clinicaId, this.pacienteNome, 4 ).subscribe( {
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

  paraTelaAgendaDia() {
    let clinicaId = this.actRoute.snapshot.paramMap.get( 'clinicaId' );
    let profissionalId = this.actRoute.snapshot.paramMap.get( 'profissionalId' );

    let dia = this.actRoute.snapshot.paramMap.get( 'dia' );
    let mes = this.actRoute.snapshot.paramMap.get( 'mes' );
    let ano = this.actRoute.snapshot.paramMap.get( 'ano' );

    this.router.navigate([ '/app', { outlets : { page : 
      'consulta-agenda-dia/'+clinicaId+'/'+profissionalId+'/'+dia+'/'+mes+'/'+ano
    } } ])
  }

}

