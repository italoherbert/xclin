import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { EspecialidadeSave } from '../bean/especialidade/especialidade-save';
import { EspecialidadeFiltro } from '../bean/especialidade/especialidade-filtro';

@Injectable({
  providedIn: 'root'
})
export class EspecialidadeService {

  constructor( private http: HttpClient ) { }

  registraEspecialidade( especialidadeSave: EspecialidadeSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/especialidade/registra', especialidadeSave, { headers: headers, withCredentials: true } )
  }

  alteraEspecialidade( id : any, especialidadeSave: EspecialidadeSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/especialidade/altera/'+id, especialidadeSave, { headers: headers, withCredentials: true } )
  }

  filtraEspecialidades( filtro: EspecialidadeFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/especialidade/filtra', filtro, { headers: headers, withCredentials: true } )
  }

  getEspecialidade( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/especialidade/get/'+id, { headers: headers, withCredentials: true } )
  }

  deletaEspecialidade( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/especialidade/deleta/'+id, { headers: headers, withCredentials: true } )
  }

}
