import { Component } from '@angular/core';
import { faAnglesLeft, faAnglesRight, faBarsProgress } from '@fortawesome/free-solid-svg-icons';
import { ConsultaService } from 'src/app/service/consulta.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-consulta-nova',
  templateUrl: './consulta-nova.component.html',
  styleUrls: ['./consulta-nova.component.css']
})
export class ConsultaNovaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faAnglesLeft : faAnglesLeft,
    faAnglesRight : faAnglesRight,
    faBarsProgress : faBarsProgress
  }

  mes : number = 0;
  ano : number = 0;
  dia : number = 0;
  turno : number = 0;

  clinicaId : number = 0;
  profissionalId : number = 0;

  quantidadesAgrupadasPorDia : any[][] = [];
  
  constructor( 
    private consultaService : ConsultaService,
    private sistemaService : SistemaService ) {}

  carregarQuantidades() {
    if ( this.profissionalId === 0 ) {
      this.erroMsg = "Selecione o profissional.";
      return;
    }

    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    this.consultaService.getQuantidadesAgrupadasPorDiaDoMes( 
              this.clinicaId, this.profissionalId, this.mes, this.ano ).subscribe( {
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

  validaDiaTurnoSelecionado() {
    this.erroMsg = null;
    
    if ( this.turno === 0 ) 
      this.erroMsg = "Selecione o turno de um dia do calendário.";
  }

  onProfissionalSelecionado( event : any ) {
    this.profissionalId = event.profissionalId;
  }

  onClinicaSelecionada( event : any ) {
    this.clinicaId = event.clinicaId;
  }

  onCalendarioAlterado( event : any ) {
    this.mes = event.mes;
    this.ano = event.ano;
  }

  onDiaTurnoSelecionado( event : any ) {
    this.ano = event.ano;
    this.mes = event.mes;
    this.dia = event.dia;
    this.turno = event.turno; 
  }

}
