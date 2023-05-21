import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { UsuarioSenhaSave } from 'src/app/bean/usuario/usuario-senha-save';
import { SistemaService } from 'src/app/service/sistema.service';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-usuario-conta-altera-senha',
  templateUrl: './usuario-conta-altera-senha.component.html',
  styleUrls: ['./usuario-conta-altera-senha.component.css']
})
export class UsuarioContaAlteraSenhaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  usuarioSave : UsuarioSenhaSave = {
    senha: '',    
  }

  senhaRepetida : any = '';

  constructor(
    private usuarioService: UsuarioService, 
    private sistemaService: SistemaService) {}

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    if ( this.usuarioSave.senha !== this.senhaRepetida ) {
      this.erroMsg = "As senhas informadas não correspondem.";
      return;
    }

    this.showSpinner = true;
       
    this.usuarioService.alteraSenhaPorUsuarioLogadoUID( this.usuarioSave ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Senha alterada com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

}
