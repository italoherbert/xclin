import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { DiretorSave } from 'src/app/core/bean/diretor/diretor-save';
import { ContaDiretorService } from 'src/app/core/service/conta-diretor.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

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
    private contaDiretorService: ContaDiretorService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.contaDiretorService.get().subscribe({
      next: (resp) => {
        this.diretorSave = resp;
        this.diretorSave.usuario.ignorarSenha = true;
  
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
        
    this.contaDiretorService.altera( this.diretorSave ).subscribe({
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
