import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { AnamneseModelo } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo';
import { AnamneseModeloFiltro } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo-filtro';
import { AnamneseModeloService } from 'src/app/core/service/anamnese-modelo.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-anamnese-modelo-tela',
  templateUrl: './anamnese-modelo-tela.component.html',
  styleUrls: ['./anamnese-modelo-tela.component.css']
})
export class AnamneseModeloTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faFilter : faFilter,
    faPlusCircle : faPlusCircle,
    faCircleInfo : faCircleInfo,
    faTrashCan : faTrashCan
  }

  anamneseModeloFiltro : AnamneseModeloFiltro = {
    filtroNome: '*'
  }

  anamneseModelos : AnamneseModelo[] = [];

  constructor( 
    private matDialog : MatDialog,
    private anamneseModeloService : AnamneseModeloService,
    private sistemaService : SistemaService ) { }

  ngOnInit() {
    this.filtra();
  }

  filtra() {
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.anamneseModeloService.filtra( this.anamneseModeloFiltro ).subscribe({
      next: (resp) => {
        this.anamneseModelos = resp;        

        if ( this.anamneseModelos.length === 0 )
          this.infoMsg = "Nenhum modelo de anamnese encontrado pelos critérios de busca informados.";

        this.showSpinner = false;        
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  remove( id : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.anamneseModeloService.deleta( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Modelo de anamnese deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open( AnamneseModeloRemoveDialog );
    dialogRef.afterClosed().subscribe( ( result ) => {
      if ( result === true )
        this.remove( id );
    } );
  }

}

@Component({
  selector: "anamnese-modelo-remove-dialog",
  templateUrl: "anamnese-modelo-remove-dialog.html"
})
export class AnamneseModeloRemoveDialog {

}

