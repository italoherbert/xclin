import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProfissionalClinicaVinculos } from '../bean/profissional/profissional-clinica-vinculos';
import { ProfissionalSave } from '../bean/profissional/profissional-save';
import { ProfissionalFiltro } from '../bean/profissional/profissional-filtro';
import { NaoAdminProfissionalFiltro } from '../bean/profissional/nao-admin-profissional-filtro';

@Injectable({
  providedIn: 'root'
})
export class ProfissionalService {

  constructor( private http: HttpClient ) { }

  getClinicaVinculos( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/profissional/clinica/vinculos/get/'+id, { headers: headers, withCredentials: true } )
  }

  salvaProfissionalClinicas( id : any, vinculos : ProfissionalClinicaVinculos ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/profissional/clinica/vinculos/salva/'+id, vinculos, { headers: headers, withCredentials: true } )
 
  }

  registraProfissional( profissionalSave: ProfissionalSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/profissional/registra', profissionalSave, { headers: headers, withCredentials: true } )
  }

  alteraProfissional( id : any, profissionalSave: ProfissionalSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/profissional/altera/'+id, profissionalSave, { headers: headers, withCredentials: true } )
  }

  alteraProfissionalPorLogadoUID( profissionalSave: ProfissionalSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/profissional/altera/logado', profissionalSave, { headers: headers, withCredentials: true } )
  }

  filtraProfissionais( filtro: ProfissionalFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/profissional/filtra', filtro, { headers: headers, withCredentials: true } )
  }

  getProfissional( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/profissional/get/'+id, { headers: headers, withCredentials: true } )
  }

  getProfissionalReg(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/profissional/get/reg', { headers: headers, withCredentials: true } );
  }

  getProfissionalEdit( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/profissional/get/edit/'+id, { headers: headers, withCredentials: true } );
  }

  getProfissionalEditPorLogadoUID(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/profissional/get/edit/logado', { headers: headers, withCredentials: true } );
  }

  getProfissionalDetalhes( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/profissional/get/detalhes/'+id, { headers: headers, withCredentials: true } );
  }

  listaPorClinica( clinicaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/profissional/lista/porclinica/'+clinicaId, { headers: headers, withCredentials: true } );
  }

  deletaProfissional( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/profissional/deleta/'+id, { headers: headers, withCredentials: true } )
  }

  filtraProfissionaisNaoAdmin( clinicaId : any, filtro: NaoAdminProfissionalFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/naoadmin/profissional/filtra/'+clinicaId, filtro, { headers: headers, withCredentials: true } );
  }

  getDetalhesProfissionalNaoAdmin( profissionalId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/profissional/get/detalhes/'+profissionalId, { headers: headers, withCredentials: true } )
  }

  getTelaProfissionalNaoAdmin(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/naoadmin/profissional/get/tela', { headers: headers, withCredentials: true } );
  }

}
