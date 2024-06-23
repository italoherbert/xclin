import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Recepcionista } from 'src/app/core/bean/recepcionista/recepcionista';
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
    filtroNome: '*'
  }

  clinicaId : number = 0;

  recepcionistasColumns : string[] = [ 'nome', 'detalhes', 'remover' ];
  recepcionistasDataSource = new MatTableDataSource<Recepcionista>([]);

  constructor( 
    private matDialog: MatDialog,
    private recepcionistaService: RecepcionistaService, 
    private sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.recepcionistaService.filtraRecepcionistas( this.clinicaId, this.recepcionistaFiltro ).subscribe({
      next: ( resp ) => {
        this.recepcionistasDataSource.data = resp;
        if ( this.recepcionistasDataSource.data.length == 0 )
          this.infoMsg = "Nenhum recepcionista encontrado.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  onClinicaSelect( clinicaId : any ) {
    this.clinicaId = clinicaId;
  }

  onClinicaSelectErroCreate( erro : any ) {
    this.erroMsg = this.sistemaService.mensagemErro( erro );
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

  mostraRemoveDialog( id : any, recepcionistaNome : any ) {
    let dialogRef = this.matDialog.open( RecepcionistaRemoveDialog, { data : { recepcionistaNome : recepcionistaNome } } );
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

  constructor( @Inject(MAT_DIALOG_DATA) public data : any ) {}

}