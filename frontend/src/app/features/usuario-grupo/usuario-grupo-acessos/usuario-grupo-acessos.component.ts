import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { Acesso } from 'src/app/core/bean/acesso/acesso';
import { UsuarioGrupo } from 'src/app/core/bean/usuario-grupo/usuario-grupo';
import { UsuarioGrupoAcessosSave } from 'src/app/core/bean/usuario-grupo/usuario-grupo-acessos-save';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { UsuarioGrupoService } from 'src/app/core/service/usuario-grupo.service';

@Component({
  selector: 'app-usuario-grupo-acessos',
  templateUrl: './usuario-grupo-acessos.component.html',
  styleUrls: ['./usuario-grupo-acessos.component.css']
})
export class UsuarioGrupoAcessosComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRotate : faRotate,
    faCircleLeft : faCircleLeft
  }

  grupo : UsuarioGrupo = {
    id: '',
    nome: '' 
  }

  acessosColumns : string[] = [ 'recurso', 'leitura', 'escrita', 'remocao' ];
  acessosDataSource = new MatTableDataSource<Acesso>([]);

  constructor( 
    private actRoute : ActivatedRoute, 
    private usuarioGrupoService: UsuarioGrupoService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.usuarioGrupoService.getGrupoEdit( id ).subscribe({
      next: ( resp ) => {
        this.grupo = resp.grupo;
        this.acessosDataSource.data = resp.acessos;

        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  sincronizaAcessos() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.usuarioGrupoService.sincronizaAcessos( id ).subscribe({
      next: ( resp ) => {
        this.acessosDataSource.data = resp;

        this.infoMsg = "Acessos sincronizados!";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    let acessosSave : UsuarioGrupoAcessosSave = {
      acessos: this.acessosDataSource.data
    };

    this.usuarioGrupoService.salvaAcessos( id, acessosSave ).subscribe({
      next: ( resp ) => {        
        this.infoMsg = "Acessos salvos com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
