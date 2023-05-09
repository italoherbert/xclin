import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DiretorSave } from '../bean/diretor/diretor-save';
import { DiretorFiltro } from '../bean/diretor/diretor-filtro';

@Injectable({
  providedIn: 'root'
})
export class DiretorService {

  constructor( private http: HttpClient ) { }

  registraDiretor( diretorSave: DiretorSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/diretor/registra', diretorSave, { headers: headers, withCredentials: true } )
  }

  alteraDiretor( id : any, diretorSave: DiretorSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/diretor/altera/'+id, diretorSave, { headers: headers, withCredentials: true } )
  }

  filtraDiretores( filtro: DiretorFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/diretor/filtra', filtro, { headers: headers, withCredentials: true } )
  }

  getDiretor( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/diretor/get/'+id, { headers: headers, withCredentials: true } )
  }

  deletaDiretor( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/diretor/deleta/'+id, { headers: headers, withCredentials: true } )
  }
}
