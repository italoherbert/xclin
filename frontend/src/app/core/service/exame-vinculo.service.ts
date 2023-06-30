import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ExameVinculoSave } from '../bean/exame-vinculo/exame-vinculo-save';
import { ExameVinculoFiltro } from '../bean/exame-vinculo/exame-vinculo-filtro';

@Injectable({
  providedIn: 'root'
})
export class ExameVinculoService {

  constructor( private http: HttpClient) { }

  registra( pacienteId : any, exameSave : ExameVinculoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/exame/vinculo/registra/'+pacienteId, exameSave, { headers: headers, withCredentials: true } );
  }

  filtra( pacienteId : any, exameFiltro : ExameVinculoFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/exame/vinculo/filtra/'+pacienteId, exameFiltro, { headers: headers, withCredentials: true } );
  }

  get( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/exame/vinculo/get/'+exameId, { headers: headers, withCredentials: true } );
  }

  deleta( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/exame/vinculo/deleta/'+exameId, { headers: headers, withCredentials: true } );
  }

}
