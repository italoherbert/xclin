import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BalancoDoDia } from '../bean/relatorio/balanco-do-dia';

@Injectable({
  providedIn: 'root'
})
export class RelatorioService {

  constructor( private http : HttpClient) { }

  getRelatorioAnamnese( pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/relatorio/anamnese/'+pacienteId, { headers : headers, withCredentials: true, responseType: 'arraybuffer' } );
  
  }

  getRelatorioBalancoDoDia( clinicaId : any, balancoDoDia : BalancoDoDia ): Observable<any> {
    let headers = new HttpHeaders( {
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.post( '/api/relatorio/balanco-do-dia/'+clinicaId, balancoDoDia, { headers : headers, withCredentials: true, responseType: 'arraybuffer' } );
  }

  getBalancoDoDiaLoad(): Observable<any> {
    let headers = new HttpHeaders( {
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    } );

    return this.http.get( '/api/relatorio/balanco-do-dia/load', { headers: headers, withCredentials: true } )
  }

}
