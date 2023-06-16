import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProfissionalClinicaVinculos } from '../bean/profissional/profissional-clinica-vinculos';
import { ProfissionalSave } from '../bean/profissional/profissional-save';
import { ProfissionalFiltro } from '../bean/profissional/profissional-filtro';
import { NaoAdminProfissionalFiltro } from '../bean/profissional/nao-admin-profissional-filtro';
import { ProfissionalEspecialidadeVinculoSave } from '../bean/profissional/profissional-especialidade-vinculo-save';

@Injectable({
  providedIn: 'root'
})
export class ProfissionalService {

  constructor( private http: HttpClient ) { }

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

  getProfissionalEspecialidadeVinculo( profissionalId : any, especialidadeId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/profissional/get/vinculo/'+profissionalId+'/'+especialidadeId, { headers: headers, withCredentials: true } )
  
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

  alteraProfissionalPorLogadoUID( profissionalSave: ProfissionalSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/conta/profissional/altera/logado', profissionalSave, { headers: headers, withCredentials: true } )
  }

  getProfissionalEditPorLogadoUID(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/get/edit/logado', { headers: headers, withCredentials: true } );
  }

  getProfissionalDetalhesPorLogadoUID(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/get/detalhes/logado', { headers: headers, withCredentials: true } );
  }

  listaEspecialidadeVinculosPorLogadoUID(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/lista/especialidades/vinculos/logado', { headers: headers, withCredentials: true } );
  }

  getSaveEspecialidadesPorLogadoUID(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/get/especialidades/save/logado', { headers: headers, withCredentials: true } );
  
  }

  getEspecialidadeVinculoPorLogadoUID( espId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/get/especialidade/vinculo/logado/'+espId, { headers: headers, withCredentials: true } );
  }

  salvaEspecialidadeVinculoPorLogadoUID( espId : any, vinculoSave: ProfissionalEspecialidadeVinculoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.put( 
      '/api/conta/profissional/salva/especialidade/vinculo/logado/'+espId, vinculoSave,
      { headers: headers, withCredentials: true } );
  }

  salvaAddEspecialidadeVinculoPorLogadoUID( espId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.post( 
      '/api/conta/profissional/salva/add/especialidade/vinculo/logado/'+espId, {},
      { headers: headers, withCredentials: true } );
  }

  deletaEspecialidadeVinculoPorLogadoUID( espId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.delete( 
      '/api/conta/profissional/deleta/especialidade/vinculo/logado/'+espId,
      { headers: headers, withCredentials: true } );
  }

}
