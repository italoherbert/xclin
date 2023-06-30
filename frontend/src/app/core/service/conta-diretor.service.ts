import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DiretorSave } from '../bean/diretor/diretor-save';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContaDiretorService {

  constructor( private http: HttpClient) { }

  altera( diretorSave: DiretorSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/conta/diretor/altera', diretorSave, { headers: headers, withCredentials: true } )
  }

  get(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/diretor/get', { headers: headers, withCredentials: true } );
  }

  loadDetalhess(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/diretor/load/detalhes', { headers: headers, withCredentials: true } );
  }

}
