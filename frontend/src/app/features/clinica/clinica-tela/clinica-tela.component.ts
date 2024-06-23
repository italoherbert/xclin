import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Clinica } from 'src/app/core/bean/clinica/clinica';
import { ClinicaFiltro } from 'src/app/core/bean/clinica/clinica-filtro';
import { ClinicaService } from 'src/app/core/service/clinica.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

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

  clinicasColumns: string[] = ['nome', 'detalhes', 'remover'];
  clinicasDataSource = new MatTableDataSource<Clinica>([]);

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
        this.clinicasDataSource.data = resp;
        if ( this.clinicasDataSource.data.length == 0 )
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

  mostraRemoveDialog( id : any, clinicaNome : any ) {
    let dialogRef = this.matDialog.open( ClinicaRemoveDialog, { data : { clinicaNome : clinicaNome} } );
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

  resposta : string = '';
  erroMsg : any = null;

  constructor( 
    public matDialogRef : MatDialogRef<ClinicaRemoveDialog>,
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
