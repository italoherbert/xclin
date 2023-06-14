import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { LancamentoSave } from '../bean/lancamento/lancamento-save';
import { LancamentoFiltro } from '../bean/lancamento/lancamento-filtro';

@Injectable({
  providedIn: 'root'
})
export class LancamentoService {

  constructor( private http: HttpClient ) { }

  registra( clinicaId : any, lancamentoSave : LancamentoSave ) : Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/lancamento/registra/'+clinicaId, lancamentoSave, { headers : headers, withCredentials: true } );
  }

  filtra( clinicaId : any, lancamentoFiltro : LancamentoFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/lancamento/filtra/'+clinicaId, lancamentoFiltro, { headers: headers, withCredentials: true } );
  }

  get( lancamentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/lancamento/get/'+lancamentoId, { headers: headers, withCredentials: true } );
  }

  getTelaLoad(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/lancamento/get/tela/load', { headers: headers, withCredentials: true } );  
  }

  getRegLoad(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/lancamento/get/reg/load', { headers: headers, withCredentials: true } );  
  }

  deleta( lancamentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/lancamento/deleta/'+lancamentoId, { headers: headers, withCredentials: true } );
  }

}
