import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';

import { UsuarioFiltro } from 'src/app/core/bean/usuario/usuario-filtro';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { UsuarioService } from 'src/app/core/service/usuario.service';

@Component({
  selector: 'app-usuario-tela',
  templateUrl: './usuario-tela.component.html',
  styleUrls: ['./usuario-tela.component.css']
})
export class UsuarioTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  usuarioFiltro : UsuarioFiltro = {
    usernameIni : ''
  }

  usuarios : any;

  constructor( 
    private matDialog: MatDialog,
    private usuarioService: UsuarioService, 
    private sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.usuarioService.filtraUsuarios( this.usuarioFiltro ).subscribe({
      next: ( resp ) => {
        this.usuarios = resp;
        if ( this.usuarios.length == 0 )
          this.infoMsg = "Nenhum usuário encontrado.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  remove( id : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.usuarioService.deletaUsuario( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Usuario deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open( UsuarioRemoveDialog );
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.remove( id );
    } );
    
  }

}


@Component({
  selector: 'usuario-remove-dialog',
  templateUrl: 'usuario-remove-dialog.html',
})
export class UsuarioRemoveDialog {

}