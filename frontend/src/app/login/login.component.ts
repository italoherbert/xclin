import { Component, OnInit } from '@angular/core';

import { Login } from '../service/bean/login';
import { SistemaService } from '../service/sistema.service';
import { UsuarioService } from '../service/usuario.service';

import { faRightToBracket } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  icons : any = {
    faRightToBracket
  };

  erroMsg : any = null;
  infoMsg : any = null;

  login : Login = {
    username : '',
    senha: ''
  };

  showSpinner : boolean = false;

  constructor( private router: Router, private usuarioService : UsuarioService, private sistemaService : SistemaService) {}

  logar() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.usuarioService.logar( this.login ).subscribe({
      next: ( resp ) => {        
        this.sistemaService.token = resp.token;
        this.showSpinner = false;
        this.router.navigate([ '/scm', { outlets : { page: 'home' } } ]);
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    })
  }

}
