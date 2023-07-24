import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { ExameFiltro } from 'src/app/core/bean/exame/exame-filtro';
import { ExameService } from 'src/app/core/service/exame.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-exame-tela',
  templateUrl: './exame-tela.component.html',
  styleUrls: ['./exame-tela.component.css']
})
export class ExameTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  exameFiltro : ExameFiltro = {
    filtroNome : '*'  
  }

  exames : any[] = [];

  clinicaId : number = 0;
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor(
    private matDialog : MatDialog, 
    private exameService: ExameService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.exameService.loadTela().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;

        if ( this.clinicasIDs.length > 0 ) 
          this.clinicaId = this.clinicasIDs[ 0 ];

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.exameService.filtra( this.clinicaId, this.exameFiltro ).subscribe({
      next: ( resp ) => {
        this.exames = resp;
        if ( this.exames.length == 0 )
          this.infoMsg = "Nenhum exame encontrado.";
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

    this.exameService.deleta( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Exame deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any, exameNome : any ) {
    let dialogRef = this.matDialog.open( ExameRemoveDialog, { data : { exameNome : exameNome }} );
    dialogRef.afterClosed().subscribe( ( result ) => {
      if ( result === true )
        this.remove( id );
    } );
  }

}

@Component({
  selector: "exame-remove-dialog",
  templateUrl: "exame-remove-dialog.html"
})
export class ExameRemoveDialog {

  constructor( @Inject(MAT_DIALOG_DATA) public data : any ) {}

}