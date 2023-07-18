import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProcedimentoSave } from '../bean/procedimento/procedimento-save';
import { ProcedimentoFiltro } from '../bean/procedimento/procedimento-filtro';

@Injectable({
  providedIn: 'root'
})
export class ProcedimentoService {

  constructor(private http: HttpClient) { }

  registra( clinicaId : any, procedimentoSave : ProcedimentoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/procedimento/registra/'+clinicaId, procedimentoSave, { headers : headers, withCredentials: true });
  }

  altera( procedimentoId : any, procedimentoSave : ProcedimentoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/procedimento/altera/'+procedimentoId, procedimentoSave, { headers : headers, withCredentials: true });
  }

  filtra( clinicaId : any, procedimentoFiltro : ProcedimentoFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/procedimento/filtra/'+clinicaId, procedimentoFiltro, { headers : headers, withCredentials: true });
  }

  get( procedimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/procedimento/get/'+procedimentoId, { headers : headers, withCredentials: true });  
  }

  loadTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/procedimento/load/tela', { headers : headers, withCredentials: true });  
  }

  loadRegTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/procedimento/load/reg', { headers : headers, withCredentials: true });  
  }

  loadEditTela( procedimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/procedimento/load/edit/'+procedimentoId, { headers : headers, withCredentials: true });  
  }

  deleta( procedimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/procedimento/deleta/'+procedimentoId, { headers : headers, withCredentials: true });  
  }

}