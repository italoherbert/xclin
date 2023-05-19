import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RecepcionistaSave } from '../bean/recepcionista/recepcionista-save';
import { RecepcionistaFiltro } from '../bean/recepcionista/recepcionista-filtro';
import { NaoAdminRecepcionistaFiltro } from '../bean/recepcionista/nao-admin-recepcionista-filtro';

@Injectable({
  providedIn: 'root'
})
export class RecepcionistaService {

  constructor( private http: HttpClient ) { }

  registraRecepcionista( recepcionistaSave: RecepcionistaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/recepcionista/registra', recepcionistaSave, { headers: headers, withCredentials: true } )
  }

  alteraRecepcionista( id : any, recepcionistaSave: RecepcionistaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/recepcionista/altera/'+id, recepcionistaSave, { headers: headers, withCredentials: true } )
  }

  filtraRecepcionistas( filtro: RecepcionistaFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/recepcionista/filtra', filtro, { headers: headers, withCredentials: true } )
  }

  getRecepcionista( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/recepcionista/get/'+id, { headers: headers, withCredentials: true } )
  }

  getRecepcionistaReg(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/recepcionista/get/reg', { headers: headers, withCredentials: true } )
  }

  getRecepcionistaEdit( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/recepcionista/get/edit/'+id, { headers: headers, withCredentials: true } )
  }

  deletaRecepcionista( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/recepcionista/deleta/'+id, { headers: headers, withCredentials: true } )
  }

  filtraRecepcionistasNaoAdmin( clinicaId : any, filtro: NaoAdminRecepcionistaFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/naoadmin/recepcionista/filtra/'+clinicaId, filtro, { headers: headers, withCredentials: true } );
  }

  getRecepcionistaNaoAdmin( diretorId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/recepcionista/get/'+diretorId, { headers: headers, withCredentials: true } )
  }

  getRecepcionistaTelaNaoAdmin(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/recepcionista/get/tela', { headers: headers, withCredentials: true } );
  }

}

