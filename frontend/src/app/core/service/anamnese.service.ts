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

  altera( anamneseId : any, anamnese: Anamnese ): Observable<any> {
    let headers = new HttpHeaders( {
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.put( '/api/anamnese/altera/'+anamneseId, anamnese, { headers : headers, withCredentials: true } );
  }

  get( anamneseId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/anamnese/get/'+anamneseId, { headers : headers, withCredentials: true } );
  }

  loadRegTela(): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/anamnese/load/reg', { headers : headers, withCredentials: true } );
  }

  loadEditTela( anamneseId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/anamnese/load/edit/'+anamneseId, { headers : headers, withCredentials: true } );
  }

}
