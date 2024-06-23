import { Component, Inject, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Usuario } from 'src/app/core/bean/usuario/usuario';

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

  usuariosColumns: string[] = ['nome', 'perfil', 'detalhes', 'remover'];
  usuariosDataSource = new MatTableDataSource<Usuario>([]);

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
        this.usuariosDataSource.data = resp;
        if ( this.usuariosDataSource.data.length == 0 )
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

  mostraRemoveDialog( id : any, username : any ) {
    let dialogRef = this.matDialog.open( UsuarioRemoveDialog, { data : { username : username}} );
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

  resposta : string = '';
  erroMsg : any = null;

  constructor(
    public matDialogRef : MatDialogRef<UsuarioRemoveDialog>,
    @Inject(MAT_DIALOG_DATA) public data : any
  ) {}

  onRemove() {
    if ( this.resposta.toLowerCase() == 'remova' ) {
      this.matDialogRef.close( true );
    } else {
      this.erroMsg = "Você não informou corretamente o nome 'remova'.";
    }
  }

}