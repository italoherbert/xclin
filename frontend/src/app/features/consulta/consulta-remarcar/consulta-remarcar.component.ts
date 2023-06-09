import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { ConsultaRemarcarSave } from 'src/app/core/bean/consulta/consulta-remarcar-save';
import { ConsultaService } from 'src/app/core/service/consulta.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

import * as moment from 'moment';

@Component({
  selector: 'app-consulta-remarcar',
  templateUrl: './consulta-remarcar.component.html',
  styleUrls: ['./consulta-remarcar.component.css']
})
export class ConsultaRemarcarComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave
  }

  dia : number = 0;
  mes : number = 0;
  ano : number = 0;
  turno : number = 0;

  quantidadesAgrupadasPorDia : any[][] = [];

  turnos : any[] = [];
  dataAtendimento : string = '';
  turnoLabel : string = '';

  consultaSave : ConsultaRemarcarSave = {
    dataAtendimento : '',
    turno : ''
  }

  constructor(
    private actRoute: ActivatedRoute,
    private consultaService: ConsultaService,
    private sistemaService: SistemaService
  ) {}

  ngOnInit() {
    this.carrega();
  }

  carrega() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let consultaId = this.actRoute.snapshot.paramMap.get( 'consultaId' );
    
    this.consultaService.getConsultaRemarcar( consultaId ).subscribe({
      next: (resp) => {
        this.turnos = resp.turnos;
        this.dataAtendimento = moment( resp.dataAtendimento, 'YYYY-MM-DD' ).format( 'DD/MM/YYYY' );
        this.turnoLabel = resp.turnoLabel;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });   
  }

  remarca() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    let consultaId = this.actRoute.snapshot.paramMap.get( 'consultaId' );

    let d = ''+this.ano+'-'+this.mes+'-'+this.dia;

    this.consultaSave.dataAtendimento = moment( d, 'YYYY-MM-DD' ).format();

    if ( this.turno < 1 ) {
      this.erroMsg = "Selecione um dia e turno no calendário.";
      return;
    }

    this.consultaSave.turno = this.turnos[ this.turno-1 ].name;

    this.showSpinner = true;

    this.consultaService.remarcaConsulta( consultaId, this.consultaSave ).subscribe({
      next: ( resp ) => {
        this.onCalendarioAlterado( { mes : this.mes, ano : this.ano } );
        this.carrega();
        this.infoMsg = "Consulta remarcada com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });      
  }

  onCalendarioAlterado( event : any ) {
    this.mes = event.mes;
    this.ano = event.ano;

    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'consultaId' );

    this.consultaService.getQuantidadesAgrupadasConsultaID( id, this.mes, this.ano ).subscribe( {
        next: (resp ) => {
          this.quantidadesAgrupadasPorDia = resp;
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
    } );

  }

  onDiaTurnoAlterado( event : any ) {
    this.dia = event.dia;
    this.mes = event.mes;
    this.ano = event.ano;
    this.turno = event.turno;
  }

}
