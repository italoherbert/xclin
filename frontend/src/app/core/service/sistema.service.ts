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
        if ( erro.error.mensagem !== undefined && erro.error.mensagem !== null )
          return erro.error.mensagem;
        return erro.message;
      case 403:
        return "Seu usuário não tem permissão para acessar este recurso.";
      default: 
        return erro.message;
    }
  }

  base64ToDownload( base64File : any, filename : string ) {
    const a = document.createElement( "a" );
    a.href = base64File;
    a.download = filename;
    a.click();
  }

  criaDownloadAncora( arrayBuffer : ArrayBuffer, filename: string ) {
    const a = document.createElement( "a" );
    a.href = URL.createObjectURL( new Blob( [arrayBuffer], { type: "application/pdf"} ) );
    a.download = filename;
    a.click();
  }

  getTimeFormat( d : string ) {    
    return moment( d, 'YYYY-MM-DD HH:mm:ss' ).format( 'HH:mm' );
  }
  
  isAdminEscopo() {
    let perfil = localStorage.getItem( 'perfil' );
    return perfil == 'RAIZ' || perfil == 'ADMIN' || perfil == 'VISITANTE';  
  }

  isRaiz() {
    let perfil = localStorage.getItem( 'perfil' );
    return perfil == 'RAIZ';
  }

  isAdmin() {
    let perfil = localStorage.getItem( 'perfil' );
    return perfil == 'ADMIN';
  }

  isSuporte() {
    let perfil = localStorage.getItem( 'perfil' );
    return perfil == 'SUPORTE';
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
