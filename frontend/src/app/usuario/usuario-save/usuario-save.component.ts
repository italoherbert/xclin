import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsuarioSave } from 'src/app/bean/usuario/usuario-save';
import { SistemaService } from 'src/app/service/sistema.service';
import { UsuarioService } from 'src/app/service/usuario.service';

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
    
  }

  usuario : UsuarioSave = {
    username : '',
    senha: '',
    perfil: '',
  }

  constructor( private actRoute : ActivatedRoute, private usuarioService: UsuarioService, private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.usuarioService.buscaUsuario( id ).subscribe({
      next: ( resp ) => {
        this.usuario.username = resp.username;
        this.usuario.perfil = resp.perfil;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
