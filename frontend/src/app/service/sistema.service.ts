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

}
