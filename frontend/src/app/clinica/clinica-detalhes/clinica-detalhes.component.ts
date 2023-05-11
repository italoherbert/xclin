import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Clinica } from 'src/app/bean/clinica/clinica';
import { Municipio } from 'src/app/bean/endereco/municipio';
import { Uf } from 'src/app/bean/endereco/uf';
import { ClinicaService } from 'src/app/service/clinica.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-clinica-detalhes',
  templateUrl: './clinica-detalhes.component.html',
  styleUrls: ['./clinica-detalhes.component.css']
})
export class ClinicaDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  clinicaDetalhes = {
    clinica : {
      id : 0,
      nome: '',
      telefone: '',
      email: '',
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
    }
  }

  constructor( private actRoute : ActivatedRoute, private clinicaService: ClinicaService, private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.clinicaService.getDetalhesClinica( id ).subscribe({
      next: ( resp ) => {
        this.clinicaDetalhes = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
