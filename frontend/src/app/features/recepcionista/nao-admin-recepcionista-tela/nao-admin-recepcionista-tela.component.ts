import { Component } from '@angular/core';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { NaoAdminRecepcionistaFiltro } from 'src/app/core/bean/recepcionista/nao-admin-recepcionista-filtro';
import { RecepcionistaService } from 'src/app/core/service/recepcionista.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-nao-admin-recepcionista-tela',
  templateUrl: './nao-admin-recepcionista-tela.component.html',
  styleUrls: ['./nao-admin-recepcionista-tela.component.css']
})
export class NaoAdminRecepcionistaTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  recepcionistaFiltro : NaoAdminRecepcionistaFiltro = {
    filtroNome : '*',
  }

  recepcionistas : any;

  clinicaId : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor( 
    private recepcionistaService: RecepcionistaService, 
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.recepcionistaService.getRecepcionistaTelaNaoAdmin().subscribe({
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

    this.recepcionistaService.filtraRecepcionistasNaoAdmin( this.clinicaId, this.recepcionistaFiltro ).subscribe({
      next: ( resp ) => {
        this.recepcionistas = resp;
        if ( this.recepcionistas.length == 0 )
          this.infoMsg = "Nenhum recepcionista encontrado.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }


}
