import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ExameSave } from '../bean/exame/exame-save';
import { ExameFiltro } from '../bean/exame/exame-filtro';

@Injectable({
  providedIn: 'root'
})
export class ExameService {

  constructor( private http: HttpClient) { }

  registra( pacienteId : any, exameSave : ExameSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/exame/registra/'+pacienteId, exameSave, { headers: headers, withCredentials: true } );
  }

  filtra( pacienteId : any, exameFiltro : ExameFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/exame/filtra/'+pacienteId, exameFiltro, { headers: headers, withCredentials: true } );
  }

  get( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/exame/get/'+exameId, { headers: headers, withCredentials: true } );
  }

  deleta( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/exame/deleta/'+exameId, { headers: headers, withCredentials: true } );
  }

}
