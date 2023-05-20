import { Injectable } from '@angular/core';

import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class SistemaService {

  constructor() { }

  mensagemErro( erro : any ) {
    switch( erro.status ) {
      case 400:
        return erro.error.mensagem;
      case 403:
        return "Seu usuário não tem permissão para acessar este recurso.";
      default: 
        return erro.message;
    }
  }

  getTimeFormat( d : string ) {    
    return moment( d, 'YYYY-MM-DD HH:mm:ss' ).format( 'HH:mm' );
  }

  isAdminEscopo() {
    let perfil = localStorage.getItem( 'perfil' );
    return perfil == 'RAIZ' || perfil == 'ADMIN';  
  }

  isProfissional() {
    let perfil = localStorage.getItem( 'perfil' );
    return perfil == 'PROFISSIONAL';
  }

  isRecepcionista() {
    let perfil = localStorage.getItem( 'perfil' );
    return perfil == 'RECEPCIONISTA';
  }

  isDiretor() {
    let perfil = localStorage.getItem( 'perfil' );
    return perfil == 'DIRETOR';
  }

}
