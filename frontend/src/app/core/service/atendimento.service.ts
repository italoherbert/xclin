import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AtendimentoRegistro } from '../bean/atendimento/atendimento-registro';
import { AtendimentoRemarcarSave } from '../bean/atendimento/atendimento-remarcar-save';
import { AtendimentoFiltro } from '../bean/atendimento/atendimento-filtro';
import { AtendimentoFilaFiltro } from '../bean/atendimento/atendimento-fila-filtro';
import { AtendimentoAlter } from '../bean/atendimento/atendimento-alter';
import { AtendimentoObservacoesAlter } from '../bean/atendimento/atendimento-observacoes-alter';
import { AtendimentoFilaCompletaFiltro } from '../bean/atendimento/atendimento-fila-completa-filtro';
import { OrcamentoPagamentoSave } from '../bean/atendimento/orcamento-pagamento-save';
import { AtendimentoRetornoSave } from '../bean/atendimento/atendimento-retorno-save';

@Injectable({
  providedIn: 'root'
})
export class AtendimentoService {

  constructor( private http: HttpClient) { }

  registraAtendimento( 
      clinicaId : any, profissionalId : any, pacienteId : any, 
      atendimentoSave: AtendimentoRegistro ): Observable<any> {

    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( 
      '/api/atendimento/registra/'+clinicaId+'/'+profissionalId+'/'+pacienteId, atendimentoSave, 
      { headers: headers, withCredentials: true } 
    );
  }

  alteraAtendimento( atendimentoId : any, atendimentoAlter: AtendimentoAlter): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.patch( '/api/atendimento/altera/'+atendimentoId, atendimentoAlter, { headers: headers, withCredentials: true } );
  }

  remarcaAtendimento( atendimentoId : any, atendimentoSave: AtendimentoRemarcarSave): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.patch( '/api/atendimento/remarca/'+atendimentoId, atendimentoSave, { headers: headers, withCredentials: true } );
  }

  registraRetorno( atendimentoId : any, retornoSave : AtendimentoRetornoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.patch( '/api/atendimento/retorno/'+atendimentoId, retornoSave, { headers: headers, withCredentials: true } );
  }

  efetuaPagamento( atendimentoId : any, pagamentoSave : OrcamentoPagamentoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/atendimento/efetua/pagamento/'+atendimentoId, pagamentoSave, { headers: headers, withCredentials: true } );  
  }

  desfazPagamento( atendimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/atendimento/desfaz/pagamento/'+atendimentoId, {}, { headers: headers, withCredentials: true } );
  }

  alteraObservacoes( atendimentoId : any, observacoesSave : AtendimentoObservacoesAlter ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/atendimento/altera/observacoes/'+atendimentoId, observacoesSave, { headers: headers, withCredentials: true } );
  }

  alteraConsultaConcluida( consultaId : any, concluida : boolean ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.patch( '/api/atendimento/alter/consulta/concluida/'+consultaId+'/'+concluida, {}, { headers: headers, withCredentials: true } );
  }

  alteraExameItemConcluido( exameItemId : any, concluido : boolean ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.patch( '/api/atendimento/alter/exameitem/concluido/'+exameItemId+'/'+concluido, {}, { headers: headers, withCredentials: true } );
  }

  alteraProcItemConcluido( procItemId : any, concluido : boolean ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.patch( '/api/atendimento/alter/procitem/concluido/'+procItemId+'/'+concluido, {}, { headers: headers, withCredentials: true } );
  }

  iniciaAtendimento( clinicaId : any, profissionalId : any, atendimentoId : any, turno : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( 
      '/api/atendimento/inicia/'+clinicaId+'/'+profissionalId+'/'+atendimentoId+'/'+turno, {}, { headers: headers, withCredentials: true } );
  }

  finalizaAtendimento( atendimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/atendimento/finaliza/'+atendimentoId, {}, { headers: headers, withCredentials: true } );  
  }

  setaParaEsperando( atendimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/atendimento/esperando/'+atendimentoId, {}, { headers: headers, withCredentials: true } );  
  }

  setaParaRegistrado( atendimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/atendimento/registrado/'+atendimentoId, {}, { headers: headers, withCredentials: true } );  
  }

  cancelaAtendimento( atendimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.patch( '/api/atendimento/cancela/'+atendimentoId, {}, { headers: headers, withCredentials: true } );  
  
  }

  listaFila( clinicaId : any, profissionalId : any, filtro : AtendimentoFilaFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/atendimento/lista/fila/'+clinicaId+'/'+profissionalId, filtro, { headers: headers, withCredentials: true } );
  }

  listaFilaCompleta( clinicaId : any, profissionalId : any, filtro : AtendimentoFilaCompletaFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/atendimento/lista/fila/completa/'+clinicaId+'/'+profissionalId, filtro, { headers: headers, withCredentials: true } );
  }

  filtraAtendimentos( clinicaId : any, filtro : AtendimentoFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/atendimento/filtra/'+clinicaId, filtro, { headers: headers, withCredentials: true } );
  }
  
  getAtendimento( atendimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/atendimento/get/'+atendimentoId, { headers: headers, withCredentials: true } );
  }

  getAtendimentoIniciado( clinicaId : any, turno : any, histObsPagaSize : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/atendimento/get/iniciado/'+clinicaId+'/'+turno+'/'+histObsPagaSize, { headers: headers, withCredentials: true } );
  
  }

  getAtendimentoReg( profissionalId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/atendimento/load/reg/'+profissionalId, { headers: headers, withCredentials: true } );
  }

  getAtendimentoRemarcar( atendimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/atendimento/load/remarcar/'+atendimentoId, { headers: headers, withCredentials: true } );
  }

  getAtendimentoRetorno(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/atendimento/load/retorno', { headers: headers, withCredentials: true } );
  }

  getAtendimentoAlter( atendimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/atendimento/load/alter/'+atendimentoId, { headers: headers, withCredentials: true } );
  }

  getAtendimentoTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/atendimento/load/tela', { headers: headers, withCredentials: true } );
  }

  getListaFilaTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/atendimento/load/fila/tela', { headers: headers, withCredentials: true } );
  }

  getIniciadaTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/atendimento/load/iniciado/tela', { headers: headers, withCredentials: true } );
  }
  
  loadNovoAtendimentoTela() : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/atendimento/load/novoatendimento/tela', { headers: headers, withCredentials: true } );
  }  

  loadPagamentoTela( atendimentoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/atendimento/load/pagamento/'+atendimentoId, { headers: headers, withCredentials: true } );
  }

  getAtendimentoAgendaTela() : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });
    return this.http.get( '/api/atendimento/load/agenda/tela', { headers: headers, withCredentials: true } );
  }

  getQuantidadesAgrupadas( clinicaId : any, profissionalId : any, mes : any, ano : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( 
      '/api/atendimento/get/quantidades/pordia/'+clinicaId+'/'+profissionalId+'/'+mes+'/'+ano, 
      { headers: headers, withCredentials: true } 
    );
  }

  getQuantidadesAgrupadasAtendimentoID( atendimentoId : any, mes : any, ano : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( 
      '/api/atendimento/get/quantidades/pordia/aid/'+atendimentoId+'/'+mes+'/'+ano, 
      { headers: headers, withCredentials: true } 
    );
  }

  deletaAtendimento( id : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/atendimento/deleta/'+id, { headers: headers, withCredentials: true } );
  }

}
