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

  registraEspecialidade( clinicaId: any, especialidadeSave: EspecialidadeSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/especialidade/registra/'+clinicaId, especialidadeSave, { headers: headers, withCredentials: true } )
  }

  alteraEspecialidade( id : any, especialidadeSave: EspecialidadeSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/especialidade/altera/'+id, especialidadeSave, { headers: headers, withCredentials: true } )
  }

  filtraEspecialidades( clinicaId : any, filtro: EspecialidadeFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/especialidade/filtra/'+clinicaId, filtro, { headers: headers, withCredentials: true } )
  }

  getEspecialidade( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/especialidade/get/'+id, { headers: headers, withCredentials: true } )
  }

  loadTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/especialidade/load/tela', { headers: headers, withCredentials: true } );
  }

  loadRegTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/especialidade/load/reg', { headers: headers, withCredentials: true } );
  }

  loadEditTela( especialidadeId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/especialidade/load/edit/'+especialidadeId, { headers: headers, withCredentials: true } );
  }

  deletaEspecialidade( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/especialidade/deleta/'+id, { headers: headers, withCredentials: true } )
  }

}
