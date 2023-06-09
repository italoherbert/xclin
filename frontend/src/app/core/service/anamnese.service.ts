import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { AnamneseSave } from '../bean/anamnese/anamnese-save';

@Injectable({
  providedIn: 'root'
})
export class AnamneseService {

  constructor( private http: HttpClient ) { }

  salvaAnamnese( pacienteId : any, anamneseSave : AnamneseSave ): Observable<any> {
    let headers = new HttpHeaders( {
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.post( '/api/paciente/anamnese/salva/'+pacienteId, anamneseSave, { headers : headers, withCredentials: true } );
  }

  getAnamnese( pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/paciente/anamnese/get/'+pacienteId, { headers : headers, withCredentials: true } );
  }

  getRegAnamnese(): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/paciente/anamnese/get/reg', { headers : headers, withCredentials: true } );
  }

  getEditAnamnese( pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/paciente/anamnese/get/edit/'+pacienteId, { headers : headers, withCredentials: true } );
  }

  getRelatorioAnamnese( pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/paciente/anamnese/relatorio/'+pacienteId, { headers : headers, withCredentials: true, responseType: 'arraybuffer' } );
  
  }

}
