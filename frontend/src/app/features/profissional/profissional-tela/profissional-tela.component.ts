import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
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
    filtroNome: '*'
  }

  clinicaId : number = 0;

  profissionais : any;

  constructor( 
    private matDialog: MatDialog,
    private profissionalService: ProfissionalService, 
    private sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.profissionalService.filtraProfissionais( this.clinicaId, this.profissionalFiltro ).subscribe({
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

  mostraRemoveDialog( id : any, profissionalNome : any ) {
    let dialogRef = this.matDialog.open( ProfissionalRemoveDialog, { data : { profissionalNome : profissionalNome } } );
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

  resposta : string = '';
  erroMsg : any = null;

  constructor( 
    public matDialogRef : MatDialogRef<ProfissionalRemoveDialog>,
    @Inject(MAT_DIALOG_DATA) public data : any ) {}

  onRemove() {
    this.erroMsg = null;
    
    if ( this.resposta.toLowerCase() == 'remova' ) {
      this.matDialogRef.close( true );
    } else {
      this.erroMsg = "Você não digitou corretamente o nome: 'remova'.";
    }
  }

}

