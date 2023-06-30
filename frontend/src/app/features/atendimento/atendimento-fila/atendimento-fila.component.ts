import { Component } from '@angular/core';
import { faChevronDown, faChevronUp, faCircleInfo, faFilter, faList } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoFilaFiltro } from 'src/app/core/bean/atendimento/atendimento-fila-filtro';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

import * as moment from 'moment';

@Component({
  selector: 'app-atendimento-fila',
  templateUrl: './atendimento-fila.component.html',
  styleUrls: ['./atendimento-fila.component.css']
})
export class AtendimentoFilaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faChevronUp : faChevronUp,
    faChevronDown : faChevronDown
  }

  atendimentoFilaFiltro : AtendimentoFilaFiltro = {
    data: '',
    turno: '',
    status: ''
  }

  clinicaId : number = 0;
  profissionalId : number = 0;

  atendimentos : any[] = [];

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  profissionaisIDs : number[] = [];
  profissionaisNomes : string[] = [];

  turnos : any[] = [];
  statuses : any[] = [];

  constructor(
    private atendimentoService: AtendimentoService, 
    private profissionalService: ProfissionalService,
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    this.atendimentoService.getListaFilaTela().subscribe({
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

          this.atendimentoFilaFiltro.turno = filaFiltro.filtro.turno;
          this.atendimentoFilaFiltro.status = filaFiltro.filtro.status;
          this.atendimentoFilaFiltro.data = filaFiltro.filtro.data;

          this.onClinicaSelecionada();
          this.filtra();
        } else {
          if ( this.clinicasIDs.length > 0 ) {          
            this.clinicaId = this.clinicasIDs[ 0 ];          

            this.onClinicaSelecionada();
          }

          if ( this.turnos.length > 0 )
            this.atendimentoFilaFiltro.turno = this.turnos[ 0 ].name;
                      
          if ( this.statuses.length > 0 )
            this.atendimentoFilaFiltro.status = this.statuses[ 0 ].name;

          this.atendimentoFilaFiltro.data = moment( new Date() ).format();
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

    this.atendimentoService.listaFila( 
          this.clinicaId, this.profissionalId, this.atendimentoFilaFiltro ).subscribe({
        next: (resp) => {
          this.atendimentos = resp;

          let filaFiltro = {
            clinicaId : this.clinicaId,
            profissionalId : this.profissionalId,            
            filtro : this.atendimentoFilaFiltro,
          }

          localStorage.setItem( 'fila-filtro', JSON.stringify( filaFiltro ) );

          if ( this.atendimentos.length == 0 )
            this.infoMsg = "Nenhuma atendimento encontrada.";

          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
    });
  }

  finalizaAtendimento( atendimentoId : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.atendimentoService.finalizaAtendimento( atendimentoId ).subscribe({
      next: (resp) => {
        this.showSpinner = false;
        this.atendimentoFilaFiltro.status = 'REGISTRADA';
        this.filtra();
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  iniciaAtendimento( atendimentoId : any, turno : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.atendimentoService.iniciaAtendimento( this.clinicaId, this.profissionalId, atendimentoId, turno ).subscribe({
      next: (resp) => {
        this.showSpinner = false;        
        this.atendimentoFilaFiltro.status = 'INICIADA';
        this.filtra();
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
