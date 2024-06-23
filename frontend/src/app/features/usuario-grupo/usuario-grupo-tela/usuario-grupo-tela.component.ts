import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';

import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { SistemaService } from 'src/app/core/service/sistema.service';

import { UsuarioGrupoFiltro } from 'src/app/core/bean/usuario-grupo/usuario-grupo-filtro';
import { UsuarioGrupoService } from 'src/app/core/service/usuario-grupo.service';
import { MatTableDataSource } from '@angular/material/table';
import { UsuarioGrupo } from 'src/app/core/bean/usuario-grupo/usuario-grupo';



@Component({
  selector: 'app-usuario-grupo-tela',
  templateUrl: './usuario-grupo-tela.component.html',
  styleUrls: ['./usuario-grupo-tela.component.css']
})
export class UsuarioGrupoTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  grupoFiltro : UsuarioGrupoFiltro = {
    nomeIni : '*'
  }

  usuarioGruposColumns : string[] = [ 'nome', 'detalhes', 'remover' ];
  usuarioGruposDataSource = new MatTableDataSource<UsuarioGrupo>([]);

  constructor( 
    private usuarioGrupoService: UsuarioGrupoService, 
    private sistemaService: SistemaService,
    private matDialog : MatDialog) {}

  ngOnInit() {
    this.filtra();
  }

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.usuarioGrupoService.filtraGrupos( this.grupoFiltro ).subscribe({
      next: ( resp ) => {
        this.usuarioGruposDataSource.data = resp;
        if ( this.usuarioGruposDataSource.data.length == 0 )
          this.infoMsg = "Nenhum grupo encontrado.";
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

    this.usuarioGrupoService.deletaGrupo( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Grupo de usuario deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  
  mostraRemoveDialog( id : any, grupoNome : any ) {
    let dialogRef = this.matDialog.open( UsuarioGrupoRemoveDialog, { data : { grupoNome : grupoNome }} );
    dialogRef.afterClosed().subscribe( 
      (result) => {
        if ( result === true )
          this.remove( id );
    } );
  }

}

@Component({
  selector: 'usuario-grupo-remove-dialog',
  templateUrl: 'usuario-grupo-remove-dialog.html',
})
export class UsuarioGrupoRemoveDialog {

  resposta : string = '';
  erroMsg : any = null;

  constructor(
    public matDialogRef : MatDialogRef<UsuarioGrupoRemoveDialog>,
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