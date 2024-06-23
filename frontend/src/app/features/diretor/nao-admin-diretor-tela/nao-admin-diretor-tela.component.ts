import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Diretor } from 'src/app/core/bean/diretor/diretor';
import { NaoAdminDiretorFiltro } from 'src/app/core/bean/diretor/nao-admin-diretor-filtro';
import { DiretorService } from 'src/app/core/service/diretor.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-nao-admin-diretor-tela',
  templateUrl: './nao-admin-diretor-tela.component.html',
  styleUrls: ['./nao-admin-diretor-tela.component.css']
})
export class NaoAdminDiretorTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  diretorFiltro : NaoAdminDiretorFiltro = {
    filtroNome : '*',
  }

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];
  clinicaId : number = 0;

  diretoresColumns: string[] = ['nome', 'detalhes'];
  diretoresDataSource = new MatTableDataSource<Diretor>([]);

  constructor( 
    private diretorService: DiretorService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.diretorService.getTelaDiretorNaoAdmin().subscribe({
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

    this.diretorService.filtraDiretoresNaoAdmin( this.clinicaId, this.diretorFiltro ).subscribe({
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

}
