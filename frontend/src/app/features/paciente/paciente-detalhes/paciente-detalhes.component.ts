import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import * as moment from 'moment';

import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { PacienteService } from 'src/app/core/service/paciente.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-paciente-detalhes',
  templateUrl: './paciente-detalhes.component.html',
  styleUrls: ['./paciente-detalhes.component.css']
})
export class PacienteDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  pacienteDetalhes = {
    paciente : {
      id : 0,
      nome: '',
      telefone: '',
      email: '',
      cpf: '',
      rg: '',
      dataNascimento: '',
      clinicaId: 0,
      clinicaNome: '',
      sexo: '',
      estadoCivil: '',
      nacionalidade: '',
      ocupacao : '',
      observacoes: '',
      anamneseCriada : false,
      endereco : {
        id : 0,
        logradouro : '',
        numero: '',
        bairro: '',
        codigoMunicipio: 0,
        codigoUf: 0
      }
    },
    uf : {
      id : 0,
      nome : ''
    },
    municipio : {
      id : 0,
      nome : ''
    },
    sexo: {
      name: '',
      label: ''
    },
    estadoCivil: {
      name: '',
      label: ''
    },
    nacionalidade: {
      name: '',
      label: ''
    }
  }



  constructor( 
    private actRoute : ActivatedRoute,
    private pacienteService: PacienteService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.pacienteService.getPacienteDetalhes( id ).subscribe({
      next: ( resp ) => {
        this.pacienteDetalhes = resp;
        this.pacienteDetalhes.paciente.dataNascimento = moment( this.pacienteDetalhes.paciente.dataNascimento ).format( 'DD/MM/YYYY' );
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
