import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConsultaRegistro } from '../bean/consulta/consulta-registro';
import { ConsultaRemarcarSave } from '../bean/consulta/consulta-remarcar-save';

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

  getNovaConsultaProfissionalSelect() : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/consulta/get/novaconsulta/profissional/select', { headers: headers, withCredentials: true } );
  }

  getQuantidadesAgrupadasPorDiaDoMes( clinicaId : any, profissionalId : any, mes : any, ano : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( 
      '/api/consulta/get/quantidades/pordia/'+clinicaId+'/'+profissionalId+'/'+mes+'/'+ano, 
      { headers: headers, withCredentials: true } 
    );
  }

  getQuantidadesAgrupadasPorDiaDoMesCID( consultaId : any, mes : any, ano : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( 
      '/api/consulta/get/quantidades/pordia/cid/'+consultaId+'/'+mes+'/'+ano, 
      { headers: headers, withCredentials: true } 
    );
  }

  listaConsultasPorData( clinicaId : any, profissionalId : any, dia : any, mes : any, ano : any ) : Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( 
      '/api/consulta/lista/pordata/'+clinicaId+'/'+profissionalId+'/'+dia+'/'+mes+'/'+ano, 
      { headers: headers, withCredentials: true } 
    );
  }

}
