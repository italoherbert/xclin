import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';

import * as moment from 'moment';

import { AtendimentoFiltro } from 'src/app/core/bean/atendimento/atendimento-filtro';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-atendimento-tela',
  templateUrl: './atendimento-tela.component.html',
  styleUrls: ['./atendimento-tela.component.css']
})
export class AtendimentoTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  atendimentoFiltro : AtendimentoFiltro = {
    dataInicio : '',
    dataFim : '',
    pacienteNomeIni: '',
    profissionalNomeIni: '',
    incluirPaciente: true,
    incluirProfissional: false,
    turno: '',
    status: '',
    incluirTodosTurnos: false,
    incluirTodosStatuses: false,
    incluirPagas : true,
    incluirRetornos : true
  }

  atendimentos : any;
  clinicaId : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  statuses : any[] = [];
  turnos : any[] = [];

  constructor( 
    private matDialog: MatDialog,
    private atendimentoService: AtendimentoService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.atendimentoService.getAtendimentoTela().subscribe({
      next: ( resp ) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;
        this.turnos = resp.turnos;
        this.statuses = resp.statuses;

        if ( this.clinicasIDs.length > 0 )
          this.clinicaId = this.clinicasIDs[ 0 ];
        if ( this.turnos.length > 0 )
          this.atendimentoFiltro.turno = this.turnos[ 0 ].name;
        if ( this.statuses.length > 0 )
          this.atendimentoFiltro.status = this.statuses[ 0 ].name; 

        this.atendimentoFiltro.dataInicio = moment( new Date() ).format();
        this.atendimentoFiltro.dataFim = moment( new Date() ).format();

        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.atendimentoService.filtraAtendimentos( this.clinicaId, this.atendimentoFiltro ).subscribe({
      next: ( resp ) => {
        this.atendimentos = resp;
        if ( this.atendimentos.length == 0 )
          this.infoMsg = "Nenhuma atendimento encontrada.";
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

    this.atendimentoService.deletaAtendimento( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Atendimento deletada com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open(AtendimentoRemoveDialog);
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.remove( id );
    } );
    
  }

}


@Component({
  selector: 'atendimento-remove-dialog',
  templateUrl: 'atendimento-remove-dialog.html',
})
export class AtendimentoRemoveDialog {

}
