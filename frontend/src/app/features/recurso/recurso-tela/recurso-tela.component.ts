import { Component, INJECTOR, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { RecursoFiltro } from 'src/app/core/bean/recurso/recurso-filtro';
import { RecursoService } from 'src/app/core/service/recurso.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-recurso-tela',
  templateUrl: './recurso-tela.component.html',
  styleUrls: ['./recurso-tela.component.css']
})
export class RecursoTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  recursoFiltro : RecursoFiltro = {
    nomeIni : '*'
  }

  recursos : any;

  constructor(
    private matDialog : MatDialog, 
    private recursoService: RecursoService, 
    private sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.recursoService.filtraRecursos( this.recursoFiltro ).subscribe({
      next: ( resp ) => {
        this.recursos = resp;
        if ( this.recursos.length == 0 )
          this.infoMsg = "Nenhum recurso encontrado.";
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

    this.recursoService.deletaRecurso( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Recurso deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any, recursoNome : any ) {
    let dialogRef = this.matDialog.open( RecursoRemoveDialog, { data : { recursoNome : recursoNome } } );
    dialogRef.afterClosed().subscribe( ( result ) => {
      if ( result === true )
        this.remove( id );
    } );
  }

}

@Component({
  selector: "recurso-remove-dialog",
  templateUrl: "recurso-remove-dialog.html"
})
export class RecursoRemoveDialog {

  constructor( @Inject(MAT_DIALOG_DATA) public data : any ) {}

}