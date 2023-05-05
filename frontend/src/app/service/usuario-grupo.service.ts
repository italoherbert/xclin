import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioGrupoService {

  constructor( private http : HttpClient ) { }

  registraGrupo( grupo : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/usuario/grupo/registra', grupo, { headers: headers, withCredentials: true } )
  }

  alteraGrupo( id : any, grupo : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/usuario/grupo/altera/'+id, grupo, { headers: headers, withCredentials: true } )
  }

  sincronizaAcessos( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/usuario/grupo/acessos/sincroniza/'+id, { headers: headers, withCredentials: true } );
  }

  salvaAcessos( id: any, acessosSave : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/usuario/grupo/acessos/salva/'+id, acessosSave, { headers: headers, withCredentials: true } );
  }

  filtraGrupos( filtro : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    
    return this.http.post( '/api/usuario/grupo/filtra', filtro, { headers: headers, withCredentials: true } );
  }

  getGrupo( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/usuario/grupo/get/'+id, { headers: headers, withCredentials: true } );
  }

  getGrupoDetalhes( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/usuario/grupo/get/detalhes/'+id, { headers: headers, withCredentials: true } );
  }

  getGrupoEdit( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/usuario/grupo/get/edit/'+id, { headers: headers, withCredentials: true } );
  }

  deletaGrupo( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    
    return this.http.delete( '/api/usuario/grupo/deleta/'+id, { headers: headers, withCredentials: true } );
  }

}
