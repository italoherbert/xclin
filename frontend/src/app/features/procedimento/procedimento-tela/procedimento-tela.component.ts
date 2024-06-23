import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Procedimento } from 'src/app/core/bean/procedimento/procedimento';
import { ProcedimentoFiltro } from 'src/app/core/bean/procedimento/procedimento-filtro';
import { ProcedimentoService } from 'src/app/core/service/procedimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-procedimento-tela',
  templateUrl: './procedimento-tela.component.html',
  styleUrls: ['./procedimento-tela.component.css']
})
export class ProcedimentoTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  procedimentoFiltro : ProcedimentoFiltro = {
    filtroNome : '*'  
  }

  clinicaId : number = 0;
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  procedimentosColumns : string[] = [ 'nome', 'detalhes', 'remover' ];
  procedimentosDataSource = new MatTableDataSource<Procedimento>([]);

  constructor(
    private matDialog : MatDialog, 
    private procedimentoService: ProcedimentoService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.procedimentoService.loadTela().subscribe({
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

    this.procedimentoService.filtra( this.clinicaId, this.procedimentoFiltro ).subscribe({
      next: ( resp ) => {
        this.procedimentosDataSource.data = resp;
        if ( this.procedimentosDataSource.data.length == 0 )
          this.infoMsg = "Nenhum procedimento encontrado.";
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

    this.procedimentoService.deleta( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Procedimento deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any, procedimentoNome : any ) {
    let dialogRef = this.matDialog.open( ProcedimentoRemoveDialog, { data : { procedimentoNome : procedimentoNome } } );
    dialogRef.afterClosed().subscribe( ( result ) => {
      if ( result === true )
        this.remove( id );
    } );
  }

}

@Component({
  selector: "procedimento-remove-dialog",
  templateUrl: "procedimento-remove-dialog.html"
})
export class ProcedimentoRemoveDialog {

  constructor( @Inject(MAT_DIALOG_DATA) public data : any ) {}

}