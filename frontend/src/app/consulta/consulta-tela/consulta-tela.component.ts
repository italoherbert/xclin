import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';

import * as moment from 'moment';

import { ConsultaFiltro } from 'src/app/bean/consulta/consulta-filtro';
import { ConsultaService } from 'src/app/service/consulta.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-consulta-tela',
  templateUrl: './consulta-tela.component.html',
  styleUrls: ['./consulta-tela.component.css']
})
export class ConsultaTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  consultaFiltro : ConsultaFiltro = {
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

  consultas : any;
  clinicaId : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  statuses : any[] = [];
  turnos : any[] = [];

  constructor( 
    private matDialog: MatDialog,
    private consultaService: ConsultaService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.consultaService.getConsultaTela().subscribe({
      next: ( resp ) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;
        this.turnos = resp.turnos;
        this.statuses = resp.statuses;

        this.consultaFiltro.dataInicio = moment( new Date() ).format();
        this.consultaFiltro.dataFim = moment( new Date() ).format();

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

    this.consultaService.filtraConsultas( this.clinicaId, this.consultaFiltro ).subscribe({
      next: ( resp ) => {
        this.consultas = resp;
        if ( this.consultas.length == 0 )
          this.infoMsg = "Nenhuma consulta encontrada.";
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

    this.consultaService.deletaConsulta( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Consulta deletada com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open(ConsultaRemoveDialog);
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.remove( id );
    } );
    
  }

}


@Component({
  selector: 'consulta-remove-dialog',
  templateUrl: 'consulta-remove-dialog.html',
})
export class ConsultaRemoveDialog {

}
