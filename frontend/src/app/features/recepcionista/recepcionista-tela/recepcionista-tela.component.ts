import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { RecepcionistaFiltro } from 'src/app/core/bean/recepcionista/recepcionista-filtro';
import { RecepcionistaService } from 'src/app/core/service/recepcionista.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-recepcionista-tela',
  templateUrl: './recepcionista-tela.component.html',
  styleUrls: ['./recepcionista-tela.component.css']
})
export class RecepcionistaTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  recepcionistaFiltro : RecepcionistaFiltro = {
    nomeIni : '',
    clinicaNomeIni: ''
  }

  recepcionistas : any;

  constructor( 
    private matDialog: MatDialog,
    private recepcionistaService: RecepcionistaService, 
    private sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.recepcionistaService.filtraRecepcionistas( this.recepcionistaFiltro ).subscribe({
      next: ( resp ) => {
        this.recepcionistas = resp;
        if ( this.recepcionistas.length == 0 )
          this.infoMsg = "Nenhum recepcionista encontrado.";
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

    this.recepcionistaService.deletaRecepcionista( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Recepcionista deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open(RecepcionistaRemoveDialog );
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.remove( id );
    } );
    
  }

}


@Component({
  selector: 'recepcionista-remove-dialog',
  templateUrl: 'recepcionista-remove-dialog.html',
})
export class RecepcionistaRemoveDialog {

}