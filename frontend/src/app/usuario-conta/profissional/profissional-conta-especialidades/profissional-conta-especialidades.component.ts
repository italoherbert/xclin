import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faEdit, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { EspecialidadeService } from 'src/app/service/especialidade.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-profissional-conta-especialidades',
  templateUrl: './profissional-conta-especialidades.component.html',
  styleUrls: ['./profissional-conta-especialidades.component.css']
})
export class ProfissionalContaEspecialidadesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faEdit : faEdit,
    faTrashCan : faTrashCan
  }

  profissionalNome : string = '';
  profissionalFuncao: string = '';
  especialidadesVinculosIDs: number[] = [];
  especialidadesVinculosNomes: string[] = [];     

  constructor( 
    private matDialog: MatDialog,
    private especialidadeService: EspecialidadeService,
    private profissionalService: ProfissionalService,
    private sistemaService: SistemaService
  ) {}

  ngOnInit() {
    this.lista();
  }

  lista() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.profissionalService.listaEspecialidadeVinculosPorLogadoUID().subscribe({
      next: (resp) => {
        this.profissionalNome = resp.profissionalNome;
        this.profissionalFuncao = resp.profissionalFuncao;
        this.especialidadesVinculosIDs = resp.especialidadesVinculosIDs;
        this.especialidadesVinculosNomes = resp.especialidadesVinculosNomes;

        this.showSpinner = false;
      },
      error: (erro) => {
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
        this.lista();

        this.infoMsg = 'Especialidade deletada com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open(ProfissionalContaEspecialidadeRemoveDialog );
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.remove( id );
    } );
    
  }

}


@Component({
  selector: 'profissional-conta-especialidade-remove-dialog',
  templateUrl: 'profissional-conta-especialidade-remove-dialog.html',
})
export class ProfissionalContaEspecialidadeRemoveDialog {

}