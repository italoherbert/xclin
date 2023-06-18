import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Login } from '../bean/login';
import { UsuarioSave } from '../bean/usuario/usuario-save';
import { UsuarioGrupoVinculosSave } from '../bean/usuario/usuario-grupo-vinculos';
import { UsuarioSenhaSave } from '../bean/usuario/usuario-senha-save';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor( private http : HttpClient ) { }

  logar( login : Login ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
    });

    return this.http.post( '/api/login', login, { headers: headers, withCredentials: true } );
  }

  listaPorClinica( clinicaId : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/usuario/lista/porclinica/'+clinicaId, { headers: headers, withCredentials: true } );
  
  }

  criaClinicaVinculo( usuarioId : any, clinicaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/usuario/clinica/vinculos/vincula/'+usuarioId+'/'+clinicaId, {}, { headers: headers, withCredentials: true } );
  }

  getClinicasVinculos( usuarioId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/usuario/clinica/vinculos/lista/'+usuarioId, { headers: headers, withCredentials: true } );
  }

  deletaClinicaVinculo( vinculoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/usuario/clinica/vinculos/deleta/'+vinculoId, { headers: headers, withCredentials: true } );
  }

  getUsuarioGrupoVinculados( id : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/usuario/grupo/vinculos/get/'+id, { headers: headers, withCredentials: true } );
  }

  salvaUsuarioGrupoVinculados( id : any, vinculosSave : UsuarioGrupoVinculosSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
  
    return this.http.put( '/api/usuario/grupo/vinculos/salva/'+id, vinculosSave, { headers: headers, withCredentials: true } );
  } 

  registraUsuario( usuario: UsuarioSave ) : Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/usuario/registra', usuario, { headers: headers, withCredentials: true } );
  }

  alteraUsuario( id : any, usuario: UsuarioSave ) : Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/usuario/altera/'+id, usuario, { headers: headers, withCredentials: true } );
  }

  alteraSenhaPorUsuarioLogadoUID( usuarioSenhaSave : UsuarioSenhaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.patch( '/api/usuario/altera/senha/logado', usuarioSenhaSave, { headers: headers, withCredentials: true } );
  
  }

  filtraUsuarios( usuarioFiltro: any ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/usuario/filtra', usuarioFiltro, { headers: headers, withCredentials: true } );
  }

  getUsuario( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/usuario/busca/'+id, { headers: headers, withCredentials: true } );
  }

  getUsuarioReg(): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/usuario/get/reg', { headers: headers, withCredentials: true } );
  }

  getUsuarioEdit( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/usuario/get/edit/'+id, { headers: headers, withCredentials: true } );
  }

  deletaUsuario( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/usuario/deleta/'+id, { headers: headers, withCredentials: true } );
  }

}
