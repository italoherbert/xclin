import { Component } from '@angular/core';

import { Login } from '../service/bean/login';
import { SistemaService } from '../service/sistema.service';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  login : Login = {
    username : '',
    senha: ''
  };

  constructor( private usuarioService : UsuarioService, private sistemaService : SistemaService) {}

  logar() {
    alert( this.login.username );
    this.usuarioService.logar( this.login ).subscribe({
      next: ( resp ) => {
        alert( resp.token );
      },
      error: ( erro ) => {

      }
    })
  }

}
