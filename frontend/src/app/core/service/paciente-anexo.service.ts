import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PacienteAnexoSave } from '../bean/paciente-anexo/paciente-anexo-save';

@Injectable({
  providedIn: 'root'
})
export class PacienteAnexoService {

  constructor( private http: HttpClient ) { }

  registra( pacienteId : any, anexoSave : PacienteAnexoSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/paciente/anexo/registra/'+pacienteId, anexoSave, { headers: headers, withCredentials: true } );
  }

  loadTela( pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/paciente/anexo/load/tela/'+pacienteId, { headers: headers, withCredentials: true } )
  }

  listaAnexos( pacienteId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/paciente/anexo/lista/'+pacienteId, { headers: headers, withCredentials: true } )
  }

  getArquivo( pacienteAnexoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/paciente/anexo/get/arquivo/'+pacienteAnexoId, { headers: headers, withCredentials: true } )
  
  }

  deleta( pacienteAnexoId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/paciente/anexo/deleta/'+pacienteAnexoId, { headers: headers, withCredentials: true } )
  }

}
