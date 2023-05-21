import { Component } from '@angular/core';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { DiretorSave } from 'src/app/bean/diretor/diretor-save';
import { DiretorService } from 'src/app/service/diretor.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-diretor-conta-alterar',
  templateUrl: './diretor-conta-alterar.component.html',
  styleUrls: ['./diretor-conta-alterar.component.css']
})
export class DiretorContaAlterarComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  diretorSave : DiretorSave = {
    nome: '',
    usuario: {
      username: '',
      senha: '',
      perfil: '',
      ignorarSenha: true
    }

  }

  constructor(
    private diretorService: DiretorService, 
    private sistemaService: SistemaService) {}

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
        
    this.diretorService.alteraDiretorPorLogadoUID( this.diretorSave ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Diretor alterado com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

}
