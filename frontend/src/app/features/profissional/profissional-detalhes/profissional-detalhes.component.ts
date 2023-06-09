import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalDetalhes } from 'src/app/core/bean/profissional/profissional-detalhes';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-profissional-detalhes',
  templateUrl: './profissional-detalhes.component.html',
  styleUrls: ['./profissional-detalhes.component.css']
})
export class ProfissionalDetalhesComponent {

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
    private actRoute : ActivatedRoute, 
    private profissionalService: ProfissionalService, 
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( this.sistemaService.isAdminEscopo() === true ) {
      this.profissionalService.getProfissionalDetalhes( id ).subscribe({
        next: ( resp ) => {
          this.profissionalDetalhes = resp;
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.profissionalService.getDetalhesProfissionalNaoAdmin( id ).subscribe({
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

}
