import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';

import { PacienteSave } from 'src/app/core/bean/paciente/paciente-save';

import { LocalidadeService } from 'src/app/core/service/localidade.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { PacienteService } from 'src/app/core/service/paciente.service';

@Component({
  selector: 'app-paciente-save',
  templateUrl: './paciente-save.component.html',
  styleUrls: ['./paciente-save.component.css']
})
export class PacienteSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  pacienteSave : PacienteSave = {
    nome: '',
    telefone: '',
    email: '',
    cpf: '',
    rg: '',
    sexo: '',
    estadoCivil: '',
    nacionalidade: '',
    ocupacao: '',
    observacao: '',
    dataNascimento: '',
    clinicaId: 0,
    clinicaNome: '',
    endereco: {
      logradouro: '',
      numero: '',
      bairro: '',
      codigoMunicipio: 0,
      codigoUf: 0
    }    
  }

  clinicaId : number = -1;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  estadosCivis : any[] = [];
  nacionalidades : any[] = [];
  sexos : any[] = [];
  ufs : any[] = [];
  municipios : any[] = [];

  constructor(
    private actRoute : ActivatedRoute, 
    private pacienteService: PacienteService, 
    private localidadeService: LocalidadeService,
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( id == '-1' ) {      
      this.pacienteService.getPacienteReg().subscribe( {
        next: ( resp ) => {
          this.clinicasIDs = resp.clinicasIDs;
          this.clinicasNomes = resp.clinicasNomes;

          this.estadosCivis = resp.estadosCivis;
          this.nacionalidades = resp.nacionalidades;
          this.sexos = resp.sexos;

          this.ufs = resp.ufs;

          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    } else {
      this.pacienteService.getPacienteEdit( id ).subscribe( {
        next: (resp) => {
          this.clinicaId = resp.paciente.clinicaId;
          this.pacienteSave = resp.paciente;      

          this.clinicasIDs = resp.clinicasIDs;
          this.clinicasNomes = resp.clinicasNomes;

          this.estadosCivis = resp.estadosCivis;
          this.nacionalidades = resp.nacionalidades;
          this.sexos = resp.sexos;

          this.ufs = resp.ufs;
          this.municipios = resp.municipios;

          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    }
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    
    if ( id === '-1' ) { 
      this.pacienteService.registraPaciente( this.clinicaId, this.pacienteSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Paciente registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.pacienteService.alteraPaciente( this.clinicaId, id, this.pacienteSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Paciente alterado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

  ufSelectionChange( ufid : number ) {
    this.showSpinner = true;
    this.localidadeService.getMunicipios( ufid ).subscribe( {
      next: (resp) => {
        this.municipios = resp;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

}
