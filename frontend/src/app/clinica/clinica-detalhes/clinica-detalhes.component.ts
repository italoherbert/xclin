import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Clinica } from 'src/app/bean/clinica/clinica';
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

  clinica : Clinica = {
    id : 0,
    nome: '',
    telefone: '',
    email: '',
    endereco : {
      id : 0,
      logradouro : '',
      numero: '',
      bairro: '',
      municipio: {
        id : 0,
        nome : ''
      },
      uf: {
        id : 0,
        nome : ''
      }
    }
  }

  constructor( private actRoute : ActivatedRoute, private clinicaService: ClinicaService, private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.clinicaService.getClinica( id ).subscribe({
      next: ( resp ) => {
        this.clinica = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
