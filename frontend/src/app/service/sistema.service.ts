import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SistemaService {

  token : any;

  constructor() { }

  mensagemErro( erro : any ) {
    switch( erro.status ) {
      case 400:
        return erro.error.mensagem;
      case 403:
        return erro.mensagem;
      default: 
        return erro.message;
    }
  }

}
