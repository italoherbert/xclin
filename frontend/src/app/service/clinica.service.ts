import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ClinicaSave } from '../bean/clinica/clinica-save';
import { ClinicaFiltro } from '../bean/clinica/clinica-filtro';

@Injectable({
  providedIn: 'root'
})
export class ClinicaService {

  constructor( private http: HttpClient ) { }

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

  getClinica( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/get/'+id, { headers: headers, withCredentials: true } )
  }

  deletaClinica( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/clinica/deleta/'+id, { headers: headers, withCredentials: true } )
  }
}
