import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { UsuarioSave } from 'src/app/core/bean/usuario/usuario-save';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { UsuarioService } from 'src/app/core/service/usuario.service';

@Component({
  selector: 'app-usuario-save',
  templateUrl: './usuario-save.component.html',
  styleUrls: ['./usuario-save.component.css']
})
export class UsuarioSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  usuarioSave : UsuarioSave = {
    username : '',
    senha: '',
    perfil: '',
    ignorarSenha: false
  }

  senhaRepetida : any = '';

  perfis : any[] = [];

  constructor( private actRoute : ActivatedRoute, private usuarioService: UsuarioService, private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( id === '-1' ) {
      this.usuarioService.getUsuarioReg().subscribe({
        next: ( resp ) => {
          this.perfis = resp.perfis;
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.usuarioService.getUsuarioEdit( id ).subscribe( {
        next: ( resp ) => {
          this.usuarioSave.username = resp.usuario.username;
          this.usuarioSave.perfil = resp.usuario.perfil;
          this.perfis = resp.perfis;
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    }
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    if ( this.usuarioSave.senha !== this.senhaRepetida ) {
      this.erroMsg = "As senhas informadas não correspondem.";
      return;
    }

    this.showSpinner = true;
    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    
    if ( id === '-1' ) { 
      this.usuarioService.registraUsuario( this.usuarioSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Usuário registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.usuarioService.alteraUsuario( id, this.usuarioSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Usuário alterado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

}
