import { Component, OnInit } from '@angular/core';

import { faRightToBracket, faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { Login } from 'src/app/core/bean/login';
import { UsuarioService } from 'src/app/core/service/usuario.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  icons : any = {
    faEye,
    faEyeSlash,
    faRightToBracket
  };

  erroMsg : any = null;
  infoMsg : any = null;

  login : Login = {
    username : '',
    senha: ''
  };

  inputType : string = 'password';
  eyeIcon : any = this.icons.faEye;

  showSpinner : boolean = false;

  constructor( 
    private router: Router, 
    private usuarioService: UsuarioService, 
    private sistemaService: SistemaService) {}

  logar() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.login.username = this.login.username.trim();

    this.usuarioService.logar( this.login ).subscribe({
      next: ( resp ) => {        
        localStorage.setItem( "token", resp.token );
        localStorage.setItem( 'username', resp.username );
        localStorage.setItem( "perfil", resp.perfil );  
        localStorage.setItem( "perfil-label", resp.perfilLabel );
        this.showSpinner = false;
        this.router.navigate([ '/app', { outlets : { page: 'home' } } ]);
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  loginInputAlterado( e : any ) {
    alert( e.keyCode );
    let code = 0;
    if ( e.target.value.length > 0 )
      code = e.target.value.charCodeAt( e.target.value.length-1 );

    if ( code == 13 )
      this.logar();
  }

  eyeClick() {
    if ( this.inputType == 'password' ) {
      this.inputType = 'text';
      this.eyeIcon = faEyeSlash;
    } else {
      this.inputType = 'password';
      this.eyeIcon = faEye;
    }
  }

}
