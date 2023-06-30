import { Component, Output, EventEmitter } from '@angular/core';
import { faAnglesLeft, faAnglesRight, faBarsProgress } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-atendimento-profissional-select',
  templateUrl: './atendimento-profissional-select.component.html',
  styleUrls: ['./atendimento-profissional-select.component.css']
})
export class AtendimentoProfissionalSelectComponent {

  @Output() onClinicaSelecionada : EventEmitter<any> = new EventEmitter();
  @Output() onProfissionalSelecionado : EventEmitter<any> = new EventEmitter();  

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faAnglesLeft : faAnglesLeft,
    faAnglesRight : faAnglesRight,
    faBarsProgress : faBarsProgress
  }

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  profissionaisIDs : number[] = [];
  profissionaisNomes : string[] = [];

  clinicaId : number = 0;
  profissionalId : number = 0;
  
  constructor( 
    private atendimentoService : AtendimentoService,
    private profissionalService : ProfissionalService, 
    private sistemaService : SistemaService ) {}


  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.atendimentoService.getNovaAtendimentoTela().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;

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

        this.onClinicaSelecionada.emit( { clinicaId : this.clinicaId } );

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } ); 
  }

  profissionalSelecionado( event : any ) {
    this.onProfissionalSelecionado.emit( { 
      clinicaId : this.clinicaId, profissionalId : this.profissionalId 
    } );
  }

}
