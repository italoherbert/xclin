import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClinicaExameSave } from '../bean/clinica-exame/clinica-exame-save';
import { ClinicaExameFiltro } from '../bean/clinica-exame/clinica-exame-filtro';

@Injectable({
  providedIn: 'root'
})
export class ClinicaExameService {

  constructor(private http: HttpClient) { }

  registra( clinicaId : any, exameSave : ClinicaExameSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/clinica/exame/registra/'+clinicaId, exameSave, { headers : headers, withCredentials: true });
  }

  altera( clinicaExameId : any, exameSave : ClinicaExameSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/clinica/exame/altera/'+clinicaExameId, exameSave, { headers : headers, withCredentials: true });
  }

  filtra( clinicaId : any, exameFiltro : ClinicaExameFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/clinica/exame/filtra/'+clinicaId, exameFiltro, { headers : headers, withCredentials: true });
  }

  get( clinicaExameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/exame/get/'+clinicaExameId, { headers : headers, withCredentials: true });  
  }

  loadTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/exame/load/tela', { headers : headers, withCredentials: true });  
  }

  loadRegTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/exame/load/reg', { headers : headers, withCredentials: true });  
  }

  loadEditTela( clinicaExameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/clinica/exame/load/edit/'+clinicaExameId, { headers : headers, withCredentials: true });  
  }

  deleta( clinicaExameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/clinica/exame/deleta/'+clinicaExameId, { headers : headers, withCredentials: true });  
  }

}
