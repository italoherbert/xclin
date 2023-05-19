import { Component } from '@angular/core';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { NaoAdminDiretorFiltro } from 'src/app/bean/diretor/nao-admin-diretor-filtro';
import { DiretorService } from 'src/app/service/diretor.service';
import { SistemaService } from 'src/app/service/sistema.service';

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
    nomeIni : '*',
  }

  diretores : any;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];
  clinicaId : number = 0;

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
        this.diretores = resp;
        if ( this.diretores.length == 0 )
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
