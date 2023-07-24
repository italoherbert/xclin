import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Paciente } from 'src/app/core/bean/paciente/paciente';
import { PacienteFiltro } from 'src/app/core/bean/paciente/paciente-filtro';
import { PacienteService } from 'src/app/core/service/paciente.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-paciente-tela',
  templateUrl: './paciente-tela.component.html',
  styleUrls: ['./paciente-tela.component.css']
})
export class PacienteTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  pacienteFiltro : PacienteFiltro = {
    nomeIni : '',
  }

  pacientes : Paciente[] = [];

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];
  clinicaId : number = -1;

  constructor( 
    private matDialog: MatDialog,
    private pacienteService: PacienteService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.pacienteService.getPacienteTela().subscribe({
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

    this.pacienteService.filtraPacientes( this.clinicaId, this.pacienteFiltro ).subscribe({
      next: ( resp ) => {
        this.pacientes = resp;        

        if ( this.pacientes.length === 0 )
          this.infoMsg = "Nenhum paciente registrado.";

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

    this.pacienteService.deletaPaciente( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Paciente deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any, pacienteNome : any ) {
    let dialogRef = this.matDialog.open( PacienteRemoveDialog, { data : { pacienteNome : pacienteNome } } );
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.remove( id );
    } );
    
  }

}


@Component({
  selector: 'paciente-remove-dialog',
  templateUrl: 'paciente-remove-dialog.html',
})
export class PacienteRemoveDialog {

  resposta : string = '';
  erroMsg : any = null;

  constructor( 
    public matDialogRef : MatDialogRef<PacienteRemoveDialog>,
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
