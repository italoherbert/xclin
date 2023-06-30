import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faAnglesLeft, faAnglesRight, faBarsProgress, faCheck } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-atendimento-agenda',
  templateUrl: './atendimento-agenda.component.html',
  styleUrls: ['./atendimento-agenda.component.css']
})
export class AtendimentoAgendaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faAnglesLeft : faAnglesLeft,
    faAnglesRight : faAnglesRight,
    faBarsProgress : faBarsProgress,
    faCheck : faCheck
  }

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  profissionaisIDs : number[] = [];
  profissionaisNomes : string[] = [];

  clinicaId : number = 0;
  profissionalId : number = 0;

  mes : number = 0;
  ano : number = 0;
  dia : number = 0;
  turno : number = 0;

  quantidadesAgrupadasPorDia : any[][] = [];

  turnos : any[] = [];
  
  constructor( 
    private router : Router,    
    private atendimentoService : AtendimentoService,
    private profissionalService : ProfissionalService, 
    private sistemaService : SistemaService ) {}


  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.atendimentoService.getAtendimentoAgendaTela().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;

        this.turnos = resp.turnos;

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

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } ); 
  }

  profissionalSelecionado( event : any ) {
    this.atualizaQuantidades();
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

  paraListagemFila() {
    if ( this.profissionalId === 0 || this.turno === 0 ) {
      this.erroMsg = "Selecione o profissional e selecione também o dia e turno no calendário";
      return;
    }

    let turno = this.turnos[ this.turno-1 ].name;

    this.router.navigate( [ '/app', { outlets : { page : 
      'atendimento-fila-completa/'+
          this.clinicaId+'/'+this.profissionalId+'/'+
          this.ano+'/'+this.mes+'/'+this.dia+'/'+turno 
    }}] );
  }


}
