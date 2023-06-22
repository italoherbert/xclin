import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { AnamneseModeloFiltro } from '../bean/anamnese-modelo/anamnese-modelo-filtro';
import { AnamneseModeloSave } from '../bean/anamnese-modelo/anamnese-modelo-save';

@Injectable({
  providedIn: 'root'
})
export class AnamneseModeloService {

  constructor( private http: HttpClient ) {}

  registra( anamneseModeloSave : AnamneseModeloSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/anamnese/modelo/registra', anamneseModeloSave, { headers: headers, withCredentials: true } );  
  }

  altera( anamneseModeloId : any, anamneseModeloSave : AnamneseModeloSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/anamnese/modelo/altera/'+anamneseModeloId, anamneseModeloSave, { headers: headers, withCredentials: true } );  
  }

  filtra( anamneseModeloFiltro : AnamneseModeloFiltro ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/anamnese/modelo/filtra', anamneseModeloFiltro, { headers: headers, withCredentials: true } );
  }

  get( anamneseModeloId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/anamnese/modelo/get/'+anamneseModeloId, { headers: headers, withCredentials: true } ); 
  }

  detalhesLoad( anamneseModeloId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/anamnese/modelo/load/detalhes/'+anamneseModeloId, { headers: headers, withCredentials: true } );   
  }

  perguntasTelaLoad( anamneseModeloId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/anamnese/modelo/load/tela/perguntas/'+anamneseModeloId, { headers: headers, withCredentials: true } );   
  }

  deleta( anamneseModeloId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/anamnese/modelo/deleta/'+anamneseModeloId, { headers: headers, withCredentials: true } );
  }

}
