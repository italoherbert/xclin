import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ClinicaSave } from '../bean/clinica/clinica-save';
import { ClinicaFiltro } from '../bean/clinica/clinica-filtro';
import { ClinicaLogoSave } from '../bean/clinica/clinica-logo-save';

@Injectable({
  providedIn: 'root'
})
export class ClinicaService {

  constructor( private http: HttpClient ) { }

  getInicialPageLogo(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/logo/initial-page', { headers: headers, withCredentials: true } )
  }

  getLogo( clinicaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/logo/'+clinicaId, { headers: headers, withCredentials: true } )
  }

  salvaLogo( clinicaId : any, logo : ClinicaLogoSave ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/clinica/logo/'+clinicaId, logo, { headers: headers, withCredentials: true } )  
  }

  paraLogoDefault( clinicaId : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/clinica/logo/to-default/'+clinicaId, {}, { headers: headers, withCredentials: true } )  
  }

  listaPorNome( nomeIni : any, limit : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/lista/limite/'+nomeIni+'/'+limit, { headers: headers, withCredentials: true } )
  }

  registraClinica( clinicaSave: ClinicaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/clinica/registra', clinicaSave, { headers: headers, withCredentials: true } )
  }

  alteraClinica( id : any, clinicaSave: ClinicaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/clinica/altera/'+id, clinicaSave, { headers: headers, withCredentials: true } )
  }

  filtraClinicas( filtro: ClinicaFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/clinica/filtra', filtro, { headers: headers, withCredentials: true } )
  }

  filtraClinicasPorNome( nomeIni : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/filtra/pornome/'+nomeIni, { headers: headers, withCredentials: true } )
  }

  getClinica( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/get/'+id, { headers: headers, withCredentials: true } )
  }

  getRegClinica(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/get/reg', { headers: headers, withCredentials: true } )
  }

  getEditClinica( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/get/edit/'+id, { headers: headers, withCredentials: true } )
  }

  getDetalhesClinica( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/get/detalhes/'+id, { headers: headers, withCredentials: true } )
  }

  deletaClinica( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/clinica/deleta/'+id, { headers: headers, withCredentials: true } )
  }

  getDetalhesClinicaNaoAdmin( clinicaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/clinica/get/detalhes/'+clinicaId, { headers: headers, withCredentials: true } )
  }

  getTelaClinicaNaoAdmin(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/clinica/get/tela', { headers: headers, withCredentials: true } );
  }

}
