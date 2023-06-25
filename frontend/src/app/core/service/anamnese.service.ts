import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Anamnese } from '../bean/anamnese/anamnese';

@Injectable({
  providedIn: 'root'
})
export class AnamneseService {

  constructor( private http: HttpClient ) { }

  vinculaModelo( pacienteId : any, anamneseModeloId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.post( '/api/anamnese/vincula/'+pacienteId+'/'+anamneseModeloId, {}, { headers : headers, withCredentials: true } );
  }

  altera( pacienteId : any, anamnese: Anamnese ): Observable<any> {
    let headers = new HttpHeaders( {
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.put( '/api/anamnese/altera/'+pacienteId, anamnese, { headers : headers, withCredentials: true } );
  }

  get( pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/anamnese/get/'+pacienteId, { headers : headers, withCredentials: true } );
  }

  loadRegTela( pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/anamnese/load/reg/'+pacienteId, { headers : headers, withCredentials: true } );
  }

  loadEditTela( pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/anamnese/load/edit/'+pacienteId, { headers : headers, withCredentials: true } );
  }

}
