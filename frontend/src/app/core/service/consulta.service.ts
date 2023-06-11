import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConsultaRegistro } from '../bean/consulta/consulta-registro';
import { ConsultaRemarcarSave } from '../bean/consulta/consulta-remarcar-save';
import { ConsultaFiltro } from '../bean/consulta/consulta-filtro';
import { ConsultaFilaFiltro } from '../bean/consulta/consulta-fila-filtro';
import { ConsultaAlter } from '../bean/consulta/consulta-alter';
import { ConsultaObservacoesAlter } from '../bean/consulta/consulta-observacoes-alter';
import { ConsultaFilaCompletaFiltro } from '../bean/consulta/consulta-fila-completa-filtro';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {

  constructor( private http: HttpClient) { }

  registraConsulta( 
      clinicaId : any, profissionalId : any, 
      especialidadeId : any, pacienteId : any, 
      consultaSave: ConsultaRegistro): Observable<any> {

    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( 
      '/api/consulta/registra/'+clinicaId+'/'+profissionalId+'/'+especialidadeId+'/'+pacienteId, consultaSave, 
      { headers: headers, withCredentials: true } 
    );
  }

  alteraConsulta( consultaId : any, consultaAlter: ConsultaAlter): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.patch( '/api/consulta/altera/'+consultaId, consultaAlter, { headers: headers, withCredentials: true } );
  }

  remarcaConsulta( consultaId : any, consultaSave: ConsultaRemarcarSave): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.patch( '/api/consulta/remarca/'+consultaId, consultaSave, { headers: headers, withCredentials: true } 
    );
  }

  registraPagamentoConsulta( consultaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/consulta/paga/'+consultaId, {}, { headers: headers, withCredentials: true } );
  }

  alteraObservacoes( consultaId : any, observacoesSave : ConsultaObservacoesAlter ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/consulta/altera/observacoes/'+consultaId, observacoesSave, { headers: headers, withCredentials: true } );
  }

  iniciaConsulta( clinicaId : any, profissionalId : any, consultaId : any, turno : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( 
      '/api/consulta/inicia/'+clinicaId+'/'+profissionalId+'/'+consultaId+'/'+turno, {}, { headers: headers, withCredentials: true } );
  }

  finalizaConsulta( consultaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/consulta/finaliza/'+consultaId, {}, { headers: headers, withCredentials: true } );  
  }

  listaFila( clinicaId : any, profissionalId : any, filtro : ConsultaFilaFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/consulta/lista/fila/'+clinicaId+'/'+profissionalId, filtro, { headers: headers, withCredentials: true } );
  }

  listaFilaCompleta( clinicaId : any, profissionalId : any, filtro : ConsultaFilaCompletaFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/consulta/lista/fila/completa/'+clinicaId+'/'+profissionalId, filtro, { headers: headers, withCredentials: true } );
  }

  filtraConsultas( clinicaId : any, filtro : ConsultaFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/consulta/filtra/'+clinicaId, filtro, { headers: headers, withCredentials: true } );
  }
  
  getConsulta( consultaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/'+consultaId, { headers: headers, withCredentials: true } );
  }

  getConsultaIniciada( clinicaId : any, turno : any, histObsPagaSize : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/iniciada/'+clinicaId+'/'+turno+'/'+histObsPagaSize, { headers: headers, withCredentials: true } );
  
  }

  getConsultaReg( profissionalId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/reg/'+profissionalId, { headers: headers, withCredentials: true } );
  }

  getConsultaRemarcar( consultaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/remarcar/'+consultaId, { headers: headers, withCredentials: true } );
  }

  getConsultaAlter( consultaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/alter/'+consultaId, { headers: headers, withCredentials: true } );
  }

  getConsultaTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/tela', { headers: headers, withCredentials: true } );
  }

  getListaFilaTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/fila/tela', { headers: headers, withCredentials: true } );
  }

  getIniciadaTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/iniciada/tela', { headers: headers, withCredentials: true } );
  }

  getNovaConsultaTela() : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/consulta/get/novaconsulta/tela', { headers: headers, withCredentials: true } );
  }

  getConsultaAgendaTela() : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/consulta/get/agenda/tela', { headers: headers, withCredentials: true } );
  }

  getQuantidadesAgrupadas( clinicaId : any, profissionalId : any, mes : any, ano : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( 
      '/api/consulta/get/quantidades/pordia/'+clinicaId+'/'+profissionalId+'/'+mes+'/'+ano, 
      { headers: headers, withCredentials: true } 
    );
  }

  getQuantidadesAgrupadasConsultaID( consultaId : any, mes : any, ano : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( 
      '/api/consulta/get/quantidades/pordia/cid/'+consultaId+'/'+mes+'/'+ano, 
      { headers: headers, withCredentials: true } 
    );
  }
 
  deletaConsulta( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/consulta/deleta/'+id, { headers: headers, withCredentials: true } );
  }

}
