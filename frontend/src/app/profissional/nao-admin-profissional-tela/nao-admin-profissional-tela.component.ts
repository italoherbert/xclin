import { Component } from '@angular/core';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { NaoAdminProfissionalFiltro } from 'src/app/bean/profissional/nao-admin-profissional-filtro';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-nao-admin-profissional-tela',
  templateUrl: './nao-admin-profissional-tela.component.html',
  styleUrls: ['./nao-admin-profissional-tela.component.css']
})
export class NaoAdminProfissionalTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  profissionalFiltro : NaoAdminProfissionalFiltro = {
    nomeIni : ''
  }

  profissionais : any;

  clinicaId : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor( 
    private profissionalService: ProfissionalService, 
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.profissionalService.getTelaProfissionalNaoAdmin().subscribe({
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

    this.profissionalService.filtraProfissionaisNaoAdmin( this.clinicaId, this.profissionalFiltro ).subscribe({
      next: ( resp ) => {
        this.profissionais = resp;
        if ( this.profissionais.length == 0 )
          this.infoMsg = "Nenhum profissional encontrado.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}

