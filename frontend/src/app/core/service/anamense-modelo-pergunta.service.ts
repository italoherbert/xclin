import { Injectable } from '@angular/core';
import { AnamneseModeloPerguntaSave } from '../bean/anamnese-modelo/anamnese-modelo-pergunta-save';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnamenseModeloPerguntaService {

  constructor( private http: HttpClient ) {}

  registra( anamneseModeloId : any, anamneseModeloPerguntaSave : AnamneseModeloPerguntaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.post( '/api/anamnese/modelo/pergunta/registra/'+anamneseModeloId, anamneseModeloPerguntaSave, { headers: headers, withCredentials: true } );  
  }

  altera( anamneseModeloPerguntaId : any, anamneseModeloPerguntaSave : AnamneseModeloPerguntaSave ): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.put( '/api/anamnese/modelo/pergunta/altera/'+anamneseModeloPerguntaId, anamneseModeloPerguntaSave, { headers: headers, withCredentials: true } );  
  }

  get( anamneseModeloPerguntaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/anamnese/modelo/pergunta/get/'+anamneseModeloPerguntaId, { headers: headers, withCredentials: true } ); 
  }

  loadRegTela(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/anamnese/modelo/pergunta/load/reg', { headers: headers, withCredentials: true } );   
  }

  loadEditTela( anamneseModeloPerguntaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.get( '/api/anamnese/modelo/pergunta/load/edit/'+anamneseModeloPerguntaId, { headers: headers, withCredentials: true } );   
  }

  deleta( anamneseModeloPerguntaId : any ): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization' : 'Bearer '+localStorage.getItem( 'token' )
    });

    return this.http.delete( '/api/anamnese/modelo/pergunta/deleta/'+anamneseModeloPerguntaId, { headers: headers, withCredentials: true } );
  }
}
