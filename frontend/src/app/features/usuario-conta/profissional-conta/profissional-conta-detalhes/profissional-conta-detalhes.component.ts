import { Component } from '@angular/core';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalDetalhes } from 'src/app/core/bean/profissional/profissional-detalhes';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-profissional-conta-detalhes',
  templateUrl: './profissional-conta-detalhes.component.html',
  styleUrls: ['./profissional-conta-detalhes.component.css']
})
export class ProfissionalContaDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  profissionalDetalhes : ProfissionalDetalhes = {
    profissional : {
      id : 0,
      nome : '',
      funcao : '',
      funcaoLabel: '',
      usuario: {
        id: 0,
        username: '',
        perfil: '',
        perfilLabel: ''
      }
    },
    clinicas: [],
    especialidades: []
  }

  constructor( 
    private profissionalService: ProfissionalService, 
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.profissionalService.getProfissionalDetalhesPorLogadoUID().subscribe({
      next: ( resp ) => {
        this.profissionalDetalhes = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });   
  }

}