import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { RecursoFiltro } from '../bean/recurso/recurso-filtro';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RecursoSave } from '../bean/recurso/recurso-save';

@Injectable({
  providedIn: 'root'
})
export class RecursoService {

  constructor( private http: HttpClient ) { }

  registraRecurso( recursoSave: RecursoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/recurso/registra', recursoSave, { headers: headers, withCredentials: true } )
  }

  alteraRecurso( id : any, recursoSave: RecursoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/recurso/altera/'+id, recursoSave, { headers: headers, withCredentials: true } )
  }

  filtraRecursos( filtro: RecursoFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/recurso/filtra', filtro, { headers: headers, withCredentials: true } )
  }

  getRecurso( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/recurso/get/'+id, { headers: headers, withCredentials: true } )
  }

  deletaRecurso( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/recurso/deleta/'+id, { headers: headers, withCredentials: true } )
  }

}
