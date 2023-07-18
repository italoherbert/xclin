import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProfissionalSave } from '../bean/profissional/profissional-save';
import { Observable } from 'rxjs';
import { ProfissionalEspecialidadeVinculoSave } from '../bean/profissional/profissional-especialidade-vinculo-save';
import { ProfissionalExameVinculoSave } from '../bean/profissional/profissional-exame-vinculo-save';
import { ProfissionalProcedimentoVinculoSave } from '../bean/profissional/profissional-procedimento-vinculo-save';

@Injectable({
  providedIn: 'root'
})
export class ContaProfissionalService {

  constructor( private http: HttpClient ) { }

  altera( profissionalSave: ProfissionalSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/conta/profissional/altera', profissionalSave, { headers: headers, withCredentials: true } )
  }

  loadEditTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/load/edit', { headers: headers, withCredentials: true } );
  }

  loadDetalhesTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/load/detalhes', { headers: headers, withCredentials: true } );
  }

  listaEspecialidadeVinculos(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/especialidade/vinculo/lista', { headers: headers, withCredentials: true } );
  }

  loadEspecialidadeVinculoSaveTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/especialidade/vinculo/load/save', { headers: headers, withCredentials: true } );
  
  }

  getEspecialidadeVinculo( espId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/especialidade/vinculo/get/'+espId, { headers: headers, withCredentials: true } );
  }

  salvaEspecialidadeVinculo( espId : any, vinculoSave: ProfissionalEspecialidadeVinculoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.put( 
      '/api/conta/profissional/especialidade/vinculo/salva/'+espId, vinculoSave,
      { headers: headers, withCredentials: true } );
  }

  vinculaEspecialidade( espId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.post( 
      '/api/conta/profissional/especialidade/vinculo/vincula/'+espId, {},
      { headers: headers, withCredentials: true } );
  }

  deletaEspecialidadeVinculo( espId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.delete( 
      '/api/conta/profissional/especialidade/vinculo/deleta/'+espId,
      { headers: headers, withCredentials: true } );
  }

  listaExamesVinculos(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/exame/vinculo/lista', { headers: headers, withCredentials: true } );
  }

  loadExameVinculoSaveTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/exame/vinculo/load/save', { headers: headers, withCredentials: true } );
  }

  getExameVinculo( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/exame/vinculo/get/'+exameId, { headers: headers, withCredentials: true } );
  }

  salvaExameVinculo( exameId : any, vinculoSave: ProfissionalExameVinculoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.put( 
      '/api/conta/profissional/exame/vinculo/salva/'+exameId, vinculoSave,
      { headers: headers, withCredentials: true } );
  }

  vinculaExame( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.post( 
      '/api/conta/profissional/exame/vinculo/vincula/'+exameId, {},
      { headers: headers, withCredentials: true } );
  }

  deletaExameVinculo( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.delete( 
      '/api/conta/profissional/exame/vinculo/deleta/'+exameId,
      { headers: headers, withCredentials: true } );
  }

  listaProcedimentosVinculos(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/procedimento/vinculo/lista', { headers: headers, withCredentials: true } );
  }

  loadProcedimentoVinculoSaveTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/procedimento/vinculo/load/save', { headers: headers, withCredentials: true } );
  }

  getProcedimentoVinculo( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/conta/profissional/procedimento/vinculo/get/'+exameId, { headers: headers, withCredentials: true } );
  }

  salvaProcedimentoVinculo( exameId : any, vinculoSave: ProfissionalProcedimentoVinculoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.put( 
      '/api/conta/profissional/procedimento/vinculo/salva/'+exameId, vinculoSave,
      { headers: headers, withCredentials: true } );
  }

  vinculaProcedimento( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.post( 
      '/api/conta/profissional/procedimento/vinculo/vincula/'+exameId, {},
      { headers: headers, withCredentials: true } );
  }

  deletaProcedimentoVinculo( exameId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });
    return this.http.delete( 
      '/api/conta/profissional/procedimento/vinculo/deleta/'+exameId,
      { headers: headers, withCredentials: true } );
  }
  
}
