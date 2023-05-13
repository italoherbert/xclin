import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faAnglesLeft, faAnglesRight, faBarsProgress } from '@fortawesome/free-solid-svg-icons';

import * as moment from 'moment';
import { ConsultaService } from 'src/app/service/consulta.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.css']
})
export class AgendaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faAnglesLeft : faAnglesLeft,
    faAnglesRight : faAnglesRight,
    faBarsProgress : faBarsProgress
  }

  meses : string[] = [ 
    'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
    'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'
  ];

  anos : number[] = [];

  ano : number = 0;
  mes : number = 0;

  quantDiasNoMes : number = 0;
  primeiroDiaDaSemana : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  profissionaisIDs : number[] = [];
  profissionaisNomes : string[] = [];

  clinicaSelecionadaID : number = 0;
  profissionalSelecionadoID : number = 0;

  quantidadesAgrupadasPorDia : any[][] = [];

  constructor( 
    private router: Router,
    private consultaService : ConsultaService,
    private profissionalService : ProfissionalService, 
    private sistemaService : SistemaService ) {}

  ngOnInit() {
    for( let i = 2000; i < 2050; i++ )
      this.anos.push( i );
      
    let dataAtual = new Date();
    this.ano = moment( dataAtual ).year();
    this.mes = moment( dataAtual ).month();

    this.geraCalendario();

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

  carregarConsultas() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    this.consultaService.getQuantidadesAgrupadasPorDiaDoMes( 
              this.clinicaSelecionadaID, this.profissionalSelecionadoID, this.mes+1, this.ano ).subscribe( {
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
  
  geraCalendario() {
    let data = moment( (this.mes+1)+'-'+this.ano, 'MM-YYYY' );
    this.quantDiasNoMes = data.daysInMonth();
    this.primeiroDiaDaSemana = data.day();    
  }

  anterior() {
    if ( this.mes == 0 ) {
      this.mes = this.meses.length-1;
      this.ano--;
    } else {
      this.mes--;
    }
    this.geraCalendario();
  }

  proximo() {
    if ( this.mes == this.meses.length-1 ) {
      this.mes = 0;
      this.ano++;
    } else {
      this.mes++;
    }
    this.geraCalendario();
  }

  labelDia( i : number, j : number ) {
    let dia =  ((i*7+j+1)-this.primeiroDiaDaSemana);
    if ( dia > 0 && dia <= this.quantDiasNoMes )
      return dia;
    return '';
  }

  isFimDeSemana( i : number, j : number ) {
    let indice =  i*7+j+1;
    return indice % 7 == 0 || (indice-1) % 7 == 0;
  }

  diaClicado( event : any, i : number, j : number ) {
    let dia =  ((i*7+j+1)-this.primeiroDiaDaSemana);
    if ( dia > 0 && dia <= this.quantDiasNoMes ) {
      this.router.navigate( [ '/app', { outlets : { page : 
        'consulta-agenda-dia/' +
          this.clinicaSelecionadaID + '/' + 
          this.profissionalSelecionadoID + '/' + 
          dia + '/' + (this.mes+1) + '/' + this.ano
      } } ] );
    }
  }

  diaQuantidade( i : number, j : number ) {
    let dia =  ((i*7+j+1)-this.primeiroDiaDaSemana);

    if ( dia > 0 && dia <= this.quantDiasNoMes ) {
      for( let k = 1; k <= this.quantDiasNoMes; k++ ) {
        for( let kk = 0; kk < this.quantidadesAgrupadasPorDia.length; kk++ ) {
          if ( this.quantidadesAgrupadasPorDia[ kk ][ 0 ] == dia ) {
            return this.quantidadesAgrupadasPorDia[ kk ][ 1 ];
          }
        }
      }
      return 0;
    }
    return '';
  }

}
