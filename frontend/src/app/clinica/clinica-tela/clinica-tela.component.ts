import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { ClinicaFiltro } from 'src/app/bean/clinica/clinica-filtro';
import { ClinicaService } from 'src/app/service/clinica.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-clinica-tela',
  templateUrl: './clinica-tela.component.html',
  styleUrls: ['./clinica-tela.component.css']
})
export class ClinicaTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  clinicaFiltro : ClinicaFiltro = {
    nomeIni : ''
  }

  clinicas : any;

  constructor(
    private matDialog : MatDialog, 
    private clinicaService: ClinicaService, 
    private sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.clinicaService.filtraClinicas( this.clinicaFiltro ).subscribe({
      next: ( resp ) => {
        this.clinicas = resp;
        if ( this.clinicas.length == 0 )
          this.infoMsg = "Nenhuma clínica encontrada.";
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

    this.clinicaService.deletaClinica( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Clinica deletada com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open( ClinicaRemoveDialog );
    dialogRef.afterClosed().subscribe( ( result ) => {
      if ( result === true )
        this.remove( id );
    } );
  }

}

@Component({
  selector: "clinica-remove-dialog",
  templateUrl: "clinica-remove-dialog.html"
})
export class ClinicaRemoveDialog {

}
