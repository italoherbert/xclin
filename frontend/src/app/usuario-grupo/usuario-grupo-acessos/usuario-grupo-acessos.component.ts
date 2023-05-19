import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { UsuarioGrupo } from 'src/app/bean/usuario-grupo/usuario-grupo';
import { UsuarioGrupoAcessosSave } from 'src/app/bean/usuario-grupo/usuario-grupo-acessos-save';
import { SistemaService } from 'src/app/service/sistema.service';
import { UsuarioGrupoService } from 'src/app/service/usuario-grupo.service';

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

  acessosSave : UsuarioGrupoAcessosSave = {
    acessos : []
  }

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
        this.acessosSave.acessos = resp.acessos;

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
        this.acessosSave.acessos = resp;

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

    this.usuarioGrupoService.salvaAcessos( id, this.acessosSave ).subscribe({
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
