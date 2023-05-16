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

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  profissionaisIDs : number[] = [];
  profissionaisNomes : string[] = [];

  clinicaSelecionadaID : number = 0;
  profissionalSelecionadoID : number = 0;

  quantidadesAgrupadasPorDia : any[][] = [];
  
  constructor( 
    private consultaService : ConsultaService,
    private profissionalService : ProfissionalService, 
    private sistemaService : SistemaService ) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.consultaService.getConsultaAgendaTela().subscribe({
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

    this.profissionalService.listaPorClinica( this.clinicaSelecionadaID ).subscribe( {
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

  carregarConsultas() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    this.consultaService.getQuantidadesAgrupadasPorDiaDoMes( 
              this.clinicaSelecionadaID, this.profissionalSelecionadoID, this.mes, this.ano ).subscribe( {
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
