import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SistemaService } from './sistema.service';

import { Login } from '../bean/usuario/login';
import { UsuarioSave } from '../bean/usuario/usuario-save';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor( private http : HttpClient, private sistemaService : SistemaService ) { }

  logar( login : Login ): Observable<any> {
    let headers = new HttpHeaders();
    headers.append( 'Content-Type', 'application/json' );

    return this.http.post( '/api/login', login, { headers: headers, withCredentials: true } );
  }

  registraUsuario( usuario: UsuarioSave ) : Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.append( 'Content-Type', 'application/json' );
    headers = headers.append( 'Authorization', 'Bearer '+localStorage.getItem( 'token' ) );

    return this.http.post( '/api/usuario/registra', usuario, { headers: headers, withCredentials: true } );
  }

  alteraUsuario( id : any, usuario: UsuarioSave ) : Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.append( 'Content-Type', 'application/json' );
    headers = headers.append( 'Authorization', 'Bearer '+localStorage.getItem( 'token' ) );

    return this.http.put( '/api/usuario/altera/'+id, usuario, { headers: headers, withCredentials: true } );
  }

  filtraUsuarios( usuarioFiltro: any ): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.append( 'Content-Type', 'application/json' );
    headers = headers.append( 'Authorization', 'Bearer '+localStorage.getItem( 'token' ) );

    return this.http.post( '/api/usuario/filtra', usuarioFiltro, { headers: headers, withCredentials: true } );
  }

  getUsuario( id : any ): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.append( 'Content-Type', 'application/json' );
    headers = headers.append( 'Authorization', 'Bearer '+localStorage.getItem( 'token' ) );

    return this.http.get( '/api/usuario/busca/'+id, { headers: headers, withCredentials: true } );
  }

  getUsuarioDadosReg(): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.append( 'Content-Type', 'application/json' );
    headers = headers.append( 'Authorization', 'Bearer '+localStorage.getItem( 'token' ) );

    return this.http.get( '/api/usuario/get/dados/reg', { headers: headers, withCredentials: true } );
  }

  getUsuarioDadosEdit( id : any ): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.append( 'Content-Type', 'application/json' );
    headers = headers.append( 'Authorization', 'Bearer '+localStorage.getItem( 'token' ) );

    return this.http.get( '/api/usuario/get/dados/edit/'+id, { headers: headers, withCredentials: true } );
  }

  deletaUsuarios( id : any ): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.append( 'Content-Type', 'application/json' );
    headers = headers.append( 'Authorization', 'Bearer '+localStorage.getItem( 'token' ) );

    return this.http.delete( '/api/usuario/deleta/'+id, { headers: headers, withCredentials: true } );
  }

}
