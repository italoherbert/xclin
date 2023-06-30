import { Component, ViewChild } from '@angular/core';
import { faAnglesLeft, faAnglesRight, faBarsProgress } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { AtendimentoRegistroComponent } from './atendimento-registro/atendimento-registro.component';
import { MatStepper } from '@angular/material/stepper';

@Component({
  selector: 'app-atendimento-novo',
  templateUrl: './atendimento-novo.component.html',
  styleUrls: ['./atendimento-novo.component.css']
})
export class AtendimentoNovoComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faAnglesLeft : faAnglesLeft,
    faAnglesRight : faAnglesRight,
    faBarsProgress : faBarsProgress
  }

  @ViewChild( "atendimentoRegistro" ) atendimentoRegistro! : AtendimentoRegistroComponent;

  mes : number = 0;
  ano : number = 0;
  dia : number = 0;
  turno : number = 0;

  clinicaId : number = 0;
  profissionalId : number = 0;

  quantidadesAgrupadasPorDia : any[][] = [];
  
  constructor( 
    private atendimentoService : AtendimentoService,
    private sistemaService : SistemaService ) {}
  
  profissionalSelectOnNext( stepper : MatStepper ) {
    this.erroMsg = null;
    this.erroMsg = null;
    
    if ( this.profissionalId === 0 ) {
      this.erroMsg = "Selecione o profissional.";      
    } else {
      stepper.next();            
      this.atualizaQuantidades();    
    }
  }

  turnoSelectOnNext( stepper : MatStepper ) {
    this.erroMsg = null;
    
    if ( this.turno === 0 ) {
      this.erroMsg = "Selecione o turno de um dia do calendário.";
    } else {
      stepper.next();
    }
  }
  
  onProfissionalSelecionado( event : any ) {
    this.profissionalId = event.profissionalId;    
    this.atendimentoRegistro.recarrega( event.profissionalId );
  }

  onClinicaSelecionada( event : any ) {
    this.clinicaId = event.clinicaId;
  }

  onCalendarioAlterado( event : any ) {
    this.mes = event.mes;
    this.ano = event.ano;    

    if ( this.profissionalId !== 0 )
      this.atualizaQuantidades();
  }

  onDiaTurnoAlterado( event : any ) {
    this.ano = event.ano;
    this.mes = event.mes;
    this.dia = event.dia;
    this.turno = event.turno; 
  }

  atualizaQuantidades() {
    this.erroMsg = null;
    this.erroMsg = null;
    this.showSpinner = true;
    
    this.atendimentoService.getQuantidadesAgrupadas( 
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

}
