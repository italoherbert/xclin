import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ExameSave } from '../bean/exame/exame-save';
import { ExameFiltro } from '../bean/exame/exame-filtro';

@Injectable({
  providedIn: 'root'
})
export class ExameService {

  constructor(private http: HttpClient) { }

  registra( clinicaId : any, exameSave : ExameSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/exame/registra/'+clinicaId, exameSave, { headers : headers, withCredentials: true });
  }

  altera( exameId : any, exameSave : ExameSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/exame/altera/'+exameId, exameSave, { headers : headers, withCredentials: true });
  }

  filtra( clinicaId : any, exameFiltro : ExameFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/exame/filtra/'+clinicaId, exameFiltro, { headers : headers, withCredentials: true });
  }

  get( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/exame/get/'+exameId, { headers : headers, withCredentials: true });  
  }

  loadTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/exame/load/tela', { headers : headers, withCredentials: true });  
  }

  loadRegTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/exame/load/reg', { headers : headers, withCredentials: true });  
  }

  loadEditTela( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/exame/load/edit/'+exameId, { headers : headers, withCredentials: true });  
  }

  deleta( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/exame/deleta/'+exameId, { headers : headers, withCredentials: true });  
  }

}
