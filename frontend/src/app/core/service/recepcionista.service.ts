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

  registraRecepcionista( clinicaId: any, recepcionistaSave: RecepcionistaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/recepcionista/registra/'+clinicaId, recepcionistaSave, { headers: headers, withCredentials: true } )
  }

  alteraRecepcionista( clinicaId: any, recepcionistaId : any, recepcionistaSave: RecepcionistaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/recepcionista/altera/'+clinicaId+'/'+recepcionistaId, recepcionistaSave, { headers: headers, withCredentials: true } )
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

  getRecepcionistaNaoAdmin( recepcionistaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/recepcionista/get/'+recepcionistaId, { headers: headers, withCredentials: true } )
  }

  getRecepcionistaTelaNaoAdmin(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/recepcionista/get/tela', { headers: headers, withCredentials: true } );
  }

  alteraRecepcionistaPorLogadoUID( clinicaId : any, recepcionistaSave: RecepcionistaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/conta/recepcionista/altera/logado/'+clinicaId, recepcionistaSave, { headers: headers, withCredentials: true } )
  }

  getRecepcionistaEditPorLogadoUID(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/recepcionista/get/edit/logado', { headers: headers, withCredentials: true } );
  }

  getRecepcionistaPorLogadoUID(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/recepcionista/get/logado', { headers: headers, withCredentials: true } );
  }

}

