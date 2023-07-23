import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DiretorSave } from '../bean/diretor/diretor-save';
import { DiretorFiltro } from '../bean/diretor/diretor-filtro';
import { DiretorClinicaVinculos } from '../bean/diretor/diretor-clinica-vinculos';
import { NaoAdminDiretorFiltro } from '../bean/diretor/nao-admin-diretor-filtro';

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

  filtraDiretores( clinicaId : any, filtro: DiretorFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/diretor/filtra/'+clinicaId, filtro, { headers: headers, withCredentials: true } );
  }

  getDiretor( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/diretor/get/'+id, { headers: headers, withCredentials: true } )
  }

  getDiretorDetalhes( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/diretor/get/detalhes/'+id, { headers: headers, withCredentials: true } )
  }

  deletaDiretor( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/diretor/deleta/'+id, { headers: headers, withCredentials: true } )
  }

  filtraDiretoresNaoAdmin( clinicaId : any, filtro: NaoAdminDiretorFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/naoadmin/diretor/filtra/'+clinicaId, filtro, { headers: headers, withCredentials: true } );
  }

  getDetalhesDiretorNaoAdmin( diretorId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/diretor/get/detalhes/'+diretorId, { headers: headers, withCredentials: true } )
  }

  getTelaDiretorNaoAdmin(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/diretor/get/tela', { headers: headers, withCredentials: true } );
  }

}
