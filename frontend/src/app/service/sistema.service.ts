import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

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

}
