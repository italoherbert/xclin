import { Component, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { faAnglesLeft, faAnglesRight, faBarsProgress } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { AtendimentoRegistroComponent } from './atendimento-registro/atendimento-registro.component';
import { MatStepper } from '@angular/material/stepper';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { CalendarioComponent } from 'src/app/shared/calendario/calendario.component';

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
  @ViewChild( "calendario" ) calendario! : CalendarioComponent;

  @ViewChild( "novoAtendimentoStepper", { read: MatStepper }  ) stepper! : MatStepper;

  mes : number = 0;
  ano : number = 0;
  dia : number = 0;
  turno : number = 0;

  clinicaId : number = 0;
  profissionalId : number = 0;

  quantidadesAgrupadasPorDia : any[][] = [];

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  profissionaisIDs : number[] = [];
  profissionaisNomes : string[] = [];
  
  constructor( 
    private atendimentoService : AtendimentoService,
    private profissionalService : ProfissionalService,
    private sistemaService : SistemaService ) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.atendimentoService.loadNovoAtendimentoTela().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;

        if ( this.clinicasIDs.length > 0 ) {
          this.clinicaId = this.clinicasIDs[ 0 ];
          this.clinicaSelecionada( null );
        }

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  clinicaSelecionada( event : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.profissionalService.listaPorClinica( this.clinicaId ).subscribe( {
      next: (resp) => {
        this.profissionaisIDs = resp.ids;
        this.profissionaisNomes = resp.nomes;

        if ( this.profissionaisIDs.length > 0 )
          this.profissionalId = this.profissionaisIDs[ 0 ];        

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } ); 
  }
    
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
      this.atendimentoRegistro.recarrega();
    }
  }

  onAtendimentoRegistrado() {
    this.atendimentoRegistro.limpaForm();
    this.calendario.limpaCalendario();

    this.dia = 0;
    this.turno = 0;

    this.clinicaId = 0;
    this.profissionalId = 0;

    this.stepper.reset();
    
    this.infoMsg = "Atendimento registrada com sucesso.";
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
