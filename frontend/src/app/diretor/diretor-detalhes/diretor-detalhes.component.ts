import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Diretor } from 'src/app/bean/diretor/diretor';
import { DiretorService } from 'src/app/service/diretor.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-diretor-detalhes',
  templateUrl: './diretor-detalhes.component.html',
  styleUrls: ['./diretor-detalhes.component.css']
})
export class DiretorDetalhesComponent {
  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  diretor : Diretor = {
    id : 0,
    nome : '',
    usuario: {
      id: 0,
      username: '',
      perfil: '',
      perfilLabel: ''
    }
  }

  constructor( 
    private actRoute : ActivatedRoute, 
    private diretorService: DiretorService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.diretorService.getDiretor( id ).subscribe({
      next: ( resp ) => {
        this.diretor = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }
  
}

