import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { UsuarioSave } from '../bean/usuario/usuario-save';


@Injectable({
  providedIn: 'root'
})
export class ContaSuporteService {

  constructor( private http : HttpClient) { }

  altera( suporteSave: UsuarioSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/conta/suporte/altera', suporteSave, { headers: headers, withCredentials: true } )
  }

  loadEdit(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/suporte/load/edit', { headers: headers, withCredentials: true } );
  }

  get(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/suporte/get', { headers: headers, withCredentials: true } );
  }

}
