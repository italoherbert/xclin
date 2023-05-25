import { Component } from '@angular/core';
import { faChevronDown, faChevronUp, faCircleInfo, faFilter, faList } from '@fortawesome/free-solid-svg-icons';
import { ConsultaFilaFiltro } from 'src/app/bean/consulta/consulta-fila-filtro';
import { ConsultaService } from 'src/app/service/consulta.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { SistemaService } from 'src/app/service/sistema.service';

import * as moment from 'moment';

@Component({
  selector: 'app-consulta-filtro-resumido',
  templateUrl: './consulta-filtro-resumido.component.html',
  styleUrls: ['./consulta-filtro-resumido.component.css']
})
export class ConsultaFiltroResumidoComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faChevronUp : faChevronUp,
    faChevronDown : faChevronDown
  }

  consultaFilaFiltro : ConsultaFilaFiltro = {
    data: '',
    turno: '',
    status: ''
  }

  clinicaId : number = 0;
  profissionalId : number = 0;

  consultas : any[] = [];

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  profissionaisIDs : number[] = [];
  profissionaisNomes : string[] = [];

  turnos : any[] = [];
  statuses : any[] = [];

  showFormFiltro : boolean = false;

  constructor(
    private consultaService: ConsultaService, 
    private profissionalService: ProfissionalService,
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let filaFiltroStr = localStorage.getItem( 'fila-filtro' );
    this.showFormFiltro = filaFiltroStr === null;
    
    this.consultaService.getConsultaFilaTela().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;

        this.turnos = resp.turnos;
        this.statuses = resp.statuses;

        let filaFiltroStr = localStorage.getItem( 'fila-filtro' );
        if ( filaFiltroStr !== null ) {
          let filaFiltro = JSON.parse( filaFiltroStr );

          this.clinicaId = filaFiltro.clinicaId;
          this.profissionalId = filaFiltro.profissionalId;

          this.consultaFilaFiltro.turno = filaFiltro.filtro.turno;
          this.consultaFilaFiltro.status = filaFiltro.filtro.status;
          this.consultaFilaFiltro.data = filaFiltro.filtro.data;

          this.onClinicaSelecionada();
          this.filtra();
        } else {
          if ( this.clinicasIDs.length > 0 ) {          
            this.clinicaId = this.clinicasIDs[ 0 ];          

            this.onClinicaSelecionada();
          }

          if ( this.turnos.length > 0 )
            this.consultaFilaFiltro.turno = this.turnos[ 0 ].name;
                      
          if ( this.statuses.length > 0 )
            this.consultaFilaFiltro.status = this.statuses[ 0 ].name;

          this.consultaFilaFiltro.data = moment( new Date() ).format();
        }

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  onClinicaSelecionada() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.profissionalService.listaPorClinica( this.clinicaId ).subscribe( {
      next: (resp) => {
        this.profissionaisIDs = resp.ids;
        this.profissionaisNomes = resp.nomes;

        let filaFiltroStr = localStorage.getItem( 'fila-filtro' );
        if ( filaFiltroStr !== null ) {
          let filaFiltro = JSON.parse( filaFiltroStr );
          this.profissionalId = filaFiltro.profissionalId;
        } else {
          if ( this.profissionaisIDs.length > 0 )
            this.profissionalId = this.profissionaisIDs[ 0 ];
        }

        this.showSpinner = false;
      }, 
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.consultaService.filtraConsultasResumido( 
          this.clinicaId, this.profissionalId, this.consultaFilaFiltro ).subscribe({
        next: (resp) => {
          this.consultas = resp;

          let filaFiltro = {
            clinicaId : this.clinicaId,
            profissionalId : this.profissionalId,            
            filtro : this.consultaFilaFiltro,
            showFormFiltro : this.showFormFiltro
          }

          localStorage.setItem( 'fila-filtro', JSON.stringify( filaFiltro ) );

          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
    });
  }

}
