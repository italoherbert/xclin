import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { DiretorFiltro } from 'src/app/core/bean/diretor/diretor-filtro';
import { DiretorService } from 'src/app/core/service/diretor.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-diretor-tela',
  templateUrl: './diretor-tela.component.html',
  styleUrls: ['./diretor-tela.component.css']
})
export class DiretorTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  diretorFiltro : DiretorFiltro = {
    filtroNome: '*'
  }

  clinicaId : number = 0;

  diretores : any;

  constructor( 
    private matDialog: MatDialog,
    private diretorService: DiretorService, 
    private sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.diretorService.filtraDiretores( this.clinicaId, this.diretorFiltro ).subscribe({
      next: ( resp ) => {
        this.diretores = resp;
        if ( this.diretores.length == 0 )
          this.infoMsg = "Nenhum diretor encontrado.";
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

    this.diretorService.deletaDiretor( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Diretor deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any, diretorNome : any ) {
    let dialogRef = this.matDialog.open( DiretorRemoveDialog, { data : { diretorNome : diretorNome } } );
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.remove( id );
    } );
    
  }

}


@Component({
  selector: 'diretor-remove-dialog',
  templateUrl: 'diretor-remove-dialog.html',
})
export class DiretorRemoveDialog {

  constructor( @Inject(MAT_DIALOG_DATA) public data : any ) {}

}
