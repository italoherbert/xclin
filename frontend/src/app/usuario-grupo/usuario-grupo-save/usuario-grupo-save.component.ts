import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { UsuarioGrupoAcessosSave } from 'src/app/bean/usuario-grupo/usuario-grupo-acessos-save';
import { UsuarioGrupoSave } from 'src/app/bean/usuario-grupo/usuario-grupo-save';
import { SistemaService } from 'src/app/service/sistema.service';
import { UsuarioGrupoService } from 'src/app/service/usuario-grupo.service';

@Component({
  selector: 'app-usuario-grupo-save',
  templateUrl: './usuario-grupo-save.component.html',
  styleUrls: ['./usuario-grupo-save.component.css']
})
export class UsuarioGrupoSaveComponent {
  infoGrupoMsg : any = null;
  erroGrupoMsg : any = null;

  infoAcessosMsg : any = null;
  erroAcessosMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRotate : faRotate,
    faCircleLeft : faCircleLeft
  }

  grupoSave : UsuarioGrupoSave = {
    nome: ''
  }

  acessosSave : UsuarioGrupoAcessosSave = {
    acessos : []
  }

  constructor( private actRoute : ActivatedRoute, private usuarioGrupoService: UsuarioGrupoService, private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoGrupoMsg = null;
    this.erroGrupoMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( id === '-1' ) {
      this.usuarioGrupoService.getGrupoReg().subscribe({
        next: ( resp ) => {
          this.grupoSave.nome = resp.grupo.nome;
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroGrupoMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.usuarioGrupoService.getGrupoEdit( id ).subscribe( {
        next: ( resp ) => {
          this.grupoSave.nome = resp.grupo.nome;
          this.acessosSave.acessos = resp.acessos;
          this.showSpinner = false;
        },
        error: ( erro ) => {
          console.log( erro );
          this.erroGrupoMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    }
  }

  salva() {
    this.infoGrupoMsg = null;
    this.erroGrupoMsg = null;

    this.showSpinner = true;
    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    
    if ( id === '-1' ) { 
      this.usuarioGrupoService.registraGrupo( this.grupoSave ).subscribe({
        next: ( resp ) => {
          this.infoGrupoMsg = "Grupo registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroGrupoMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.usuarioGrupoService.alteraGrupo( id, this.grupoSave ).subscribe({
        next: ( resp ) => {
          this.infoGrupoMsg = "Grupo alterado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroGrupoMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

  sincronizaAcessos() {

  }

  salvaAcessos() {

  }

}