import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Observable } from 'rxjs';
import { Diretor } from 'src/app/core/bean/diretor/diretor';
import { DiretorFiltro } from 'src/app/core/bean/diretor/diretor-filtro';
import { DiretorService } from 'src/app/core/service/diretor.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-diretor-tela',
  templateUrl: './diretor-tela.component.html',
  styleUrls: ['./diretor-tela.component.css']
})
export class DiretorTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  diretorFiltro : DiretorFiltro = {
    filtroNome: '*'
  }

  clinicaId : number = 0;
  todosFlag : boolean = false;

  diretoresColumns: string[] = ['nome', 'detalhes', 'remover'];
  diretoresDataSource = new MatTableDataSource<Diretor>([]);

  constructor( 
    private matDialog: MatDialog,
    private diretorService: DiretorService, 
    public sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let observable : Observable<any>;
    if ( this.todosFlag === true ) {
      observable = this.diretorService.filtraDiretoresTodos( this.diretorFiltro );
    } else {
      observable = this.diretorService.filtraDiretores( this.clinicaId, this.diretorFiltro );
    }

    observable.subscribe({
      next: ( resp ) => {
        this.diretoresDataSource.data = resp;
        if ( this.diretoresDataSource.data.length == 0 )
          this.infoMsg = "Nenhum diretor encontrado.";
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

    this.diretorService.deletaDiretor( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Diretor deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any, diretorNome : any ) {
    let dialogRef = this.matDialog.open( DiretorRemoveDialog, { data : { diretorNome : diretorNome } } );
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.remove( id );
    } );
    
  }

}


@Component({
  selector: 'diretor-remove-dialog',
  templateUrl: 'diretor-remove-dialog.html',
})
export class DiretorRemoveDialog {

  constructor( @Inject(MAT_DIALOG_DATA) public data : any ) {}

}
