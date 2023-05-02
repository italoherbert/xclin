import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from './bean/login';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SistemaService } from './sistema.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor( private http : HttpClient, private sistemaService : SistemaService ) { }

  logar( login : Login ): Observable<any> {
    let headers = new HttpHeaders();
    headers.append( 'Content-Type', 'application/json' );

    return this.http.post( '/api/login', login, { headers: headers, withCredentials: true } );
  }

}
