import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocalidadeService {

  constructor( private http: HttpClient ) { }

  getMunicipios( ufid : number ): Observable<any> {
    return this.http.get( '/api/localidade/uf/'+ufid+'/municipios' );
  }

}