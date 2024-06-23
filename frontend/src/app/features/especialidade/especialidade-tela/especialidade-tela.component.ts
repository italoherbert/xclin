import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Especialidade } from 'src/app/core/bean/especialidade/especialidade';
import { EspecialidadeFiltro } from 'src/app/core/bean/especialidade/especialidade-filtro';
import { EspecialidadeService } from 'src/app/core/service/especialidade.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-especialidade-tela',
  templateUrl: './especialidade-tela.component.html',
  styleUrls: ['./especialidade-tela.component.css']
})
export class EspecialidadeTelaComponent {
  
  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  especialidadeFiltro : EspecialidadeFiltro = {
    nomeIni : '*'
  }
  
  clinicaId : number = 0;  
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  especialidadesColumns: string[] = ['nome', 'detalhes', 'remover'];
  especialidadesDataSource = new MatTableDataSource<Especialidade>([]);

  constructor(
    private matDialog : MatDialog, 
    private especialidadeService: EspecialidadeService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.especialidadeService.loadTela().subscribe({
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

    this.especialidadeService.filtraEspecialidades( this.clinicaId, this.especialidadeFiltro ).subscribe({
      next: ( resp ) => {
        this.especialidadesDataSource.data = resp;
        if ( this.especialidadesDataSource.data.length == 0 )
          this.infoMsg = "Nenhuma especialidade encontrada.";
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

    this.especialidadeService.deletaEspecialidade( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Especialidade deletada com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any, especialidadeNome : any ) {
    let dialogRef = this.matDialog.open( EspecialidadeRemoveDialog, { data : { especialidadeNome: especialidadeNome }} );
    dialogRef.afterClosed().subscribe( ( result ) => {
      if ( result === true )
        this.remove( id );
    } );
  }

}

@Component({
  selector: "especialidade-remove-dialog",
  templateUrl: "especialidade-remove-dialog.html"
})
export class EspecialidadeRemoveDialog {

  constructor( @Inject(MAT_DIALOG_DATA) public data : any ) {}

}
