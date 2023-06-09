import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalFiltro } from 'src/app/core/bean/profissional/profissional-filtro';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-profissional-tela',
  templateUrl: './profissional-tela.component.html',
  styleUrls: ['./profissional-tela.component.css']
})
export class ProfissionalTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  profissionalFiltro : ProfissionalFiltro = {
    nomeIni : '',
    clinicaNomeIni: ''
  }

  profissionais : any;

  constructor( 
    private matDialog: MatDialog,
    private profissionalService: ProfissionalService, 
    private sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.profissionalService.filtraProfissionais( this.profissionalFiltro ).subscribe({
      next: ( resp ) => {
        this.profissionais = resp;
        if ( this.profissionais.length == 0 )
          this.infoMsg = "Nenhum profissional encontrado.";
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

    this.profissionalService.deletaProfissional( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Profissional deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open(ProfissionalRemoveDialog );
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.remove( id );
    } );
    
  }

}


@Component({
  selector: 'profissional-remove-dialog',
  templateUrl: 'profissional-remove-dialog.html',
})
export class ProfissionalRemoveDialog {

}

