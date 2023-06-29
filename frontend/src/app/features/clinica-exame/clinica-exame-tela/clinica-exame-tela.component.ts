import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { ClinicaExameFiltro } from 'src/app/core/bean/clinica-exame/clinica-exame-filtro';
import { ClinicaExameService } from 'src/app/core/service/clinica-exame.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-clinica-exame-tela',
  templateUrl: './clinica-exame-tela.component.html',
  styleUrls: ['./clinica-exame-tela.component.css']
})
export class ClinicaExameTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  exameFiltro : ClinicaExameFiltro = {
    filtroNome : '*'  
  }

  exames : any[] = [];

  clinicaId : number = 0;
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor(
    private matDialog : MatDialog, 
    private clinicaExameService: ClinicaExameService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.clinicaExameService.loadTela().subscribe({
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

    this.clinicaExameService.filtra( this.clinicaId, this.exameFiltro ).subscribe({
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

    this.clinicaExameService.deleta( id ).subscribe({
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

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open( ClinicaExameRemoveDialog );
    dialogRef.afterClosed().subscribe( ( result ) => {
      if ( result === true )
        this.remove( id );
    } );
  }

}

@Component({
  selector: "clinica-exame-remove-dialog",
  templateUrl: "clinica-exame-remove-dialog.html"
})
export class ClinicaExameRemoveDialog {

}