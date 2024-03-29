import { Component } from '@angular/core';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { DiretorDetalhes } from 'src/app/core/bean/diretor/diretor-detalhes';
import { ContaDiretorService } from 'src/app/core/service/conta-diretor.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-diretor-conta-detalhes',
  templateUrl: './diretor-conta-detalhes.component.html',
  styleUrls: ['./diretor-conta-detalhes.component.css']
})
export class DiretorContaDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  diretorDetalhes : DiretorDetalhes = {
    diretor : {
      id : 0,
      nome : '',
      usuario: {
        id: 0,
        username: '',
        perfil: '',
        perfilLabel: ''
      }
    },
    clinicas: []
  }

  constructor( 
    private contaDiretorService: ContaDiretorService, 
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.contaDiretorService.loadDetalhess().subscribe({
      next: ( resp ) => {
        this.diretorDetalhes = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });   
  }

}
