import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { PacienteSave } from '../bean/paciente/paciente-save';
import { PacienteFiltro } from '../bean/paciente/paciente-filtro';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {

  constructor( private http: HttpClient ) { }

  registraPaciente( clinicaId : any, pacienteSave: PacienteSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/paciente/registra/'+clinicaId, pacienteSave, { headers: headers, withCredentials: true } )
  }

  alteraPaciente( clinicaId : any, pacienteId : any, pacienteSave: PacienteSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/paciente/altera/'+clinicaId+'/'+pacienteId, pacienteSave, { headers: headers, withCredentials: true } )
  }

  filtraPacientes( clinicaId : any, filtro: PacienteFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/paciente/filtra/'+clinicaId, filtro, { headers: headers, withCredentials: true } )
  }

  getPaciente( clinicaId : any, pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/paciente/get/'+clinicaId+'/'+pacienteId, { headers: headers, withCredentials: true } )
  }

  getPacienteTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/paciente/get/tela', { headers: headers, withCredentials: true } )
  }

  getPacienteReg(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/paciente/get/reg', { headers: headers, withCredentials: true } )
  }

  getPacienteEdit( clinicaId : any, pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/paciente/get/edit/'+clinicaId+'/'+pacienteId, { headers: headers, withCredentials: true } )
  }

  getPacienteDetalhes( clinicaId : any, pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/paciente/get/detalhes/'+clinicaId+'/'+pacienteId, { headers: headers, withCredentials: true } )
  }

  deletaPaciente( clinicaId : any, pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/paciente/deleta/'+clinicaId+'/'+pacienteId, { headers: headers, withCredentials: true } )
  }
}

