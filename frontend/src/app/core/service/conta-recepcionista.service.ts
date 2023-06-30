import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RecepcionistaSave } from '../bean/recepcionista/recepcionista-save';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContaRecepcionistaService {

  constructor( private http : HttpClient) { }

  altera( clinicaId : any, recepcionistaSave: RecepcionistaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/conta/recepcionista/altera/'+clinicaId, recepcionistaSave, { headers: headers, withCredentials: true } )
  }

  loadEdit(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/recepcionista/load/edit', { headers: headers, withCredentials: true } );
  }

  get(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/recepcionista/get', { headers: headers, withCredentials: true } );
  }

}
