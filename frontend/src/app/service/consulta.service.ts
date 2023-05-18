import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConsultaRegistro } from '../bean/consulta/consulta-registro';
import { ConsultaRemarcarSave } from '../bean/consulta/consulta-remarcar-save';
import { ConsultaFiltro } from '../bean/consulta/consulta-filtro';
import { ConsultaFilaFiltro } from '../bean/consulta/consulta-fila-filtro';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {

  constructor( private http: HttpClient) { }

  registraConsulta( clinicaId : any, profissionalId : any, pacienteId : any, consultaSave: ConsultaRegistro): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( 
      '/api/consulta/registra/'+clinicaId+'/'+profissionalId+'/'+pacienteId, consultaSave, 
      { headers: headers, withCredentials: true } 
    );
  }

  remarcaConsulta( consultaId : any, consultaSave: ConsultaRemarcarSave): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/consulta/remarca/'+consultaId, consultaSave, { headers: headers, withCredentials: true } 
    );
  }

  listaFilaConsultas( clinicaId : any, profissionalId : any, filtro : ConsultaFilaFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/consulta/lista/fila/'+clinicaId+'/'+profissionalId, filtro, { headers: headers, withCredentials: true } );
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

  getConsultaReg(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/reg', { headers: headers, withCredentials: true } );
  }

  getConsultaTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/tela', { headers: headers, withCredentials: true } );
  }

  getConsultaFilaTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/fila/tela', { headers: headers, withCredentials: true } );
  }

  getNovaConsultaProfissionalSelect() : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/novaconsulta/profissional/select', { headers: headers, withCredentials: true } );
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
