import { Component, EventEmitter, Output, Input } from '@angular/core';
import { faAnglesLeft, faAnglesRight, faBarsProgress } from '@fortawesome/free-solid-svg-icons';

import * as moment from 'moment';

@Component({
  selector: 'app-calendario',
  templateUrl: './calendario.component.html',
  styleUrls: ['./calendario.component.css']
})
export class CalendarioComponent {

  @Output() onDiaClicado : EventEmitter<any> = new EventEmitter();
  @Output() onCalendarioAlterado : EventEmitter<any> = new EventEmitter();

  @Input() quantidades : any[][] = [];

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
  mesI : number = 0;

  quantDiasNoMes : number = 0;
  primeiroDiaDaSemana : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  profissionaisIDs : number[] = [];
  profissionaisNomes : string[] = [];

  clinicaSelecionadaID : number = 0;
  profissionalSelecionadoID : number = 0;

  quantidadesAgrupadasPorDia : any[][] = [];

  constructor() {}

  ngOnInit() {      
    let dataAtual = new Date();
    this.ano = moment( dataAtual ).year();
    this.mesI = moment( dataAtual ).month();

    for( let i = -20; i <= 20; i++ )
      this.anos.push( this.ano + i );

    this.geraCalendario();
  }

  geraCalendario() {
    let data = moment( (this.mesI+1)+'-'+this.ano, 'MM-YYYY' );
    this.quantDiasNoMes = data.daysInMonth();
    this.primeiroDiaDaSemana = data.day();    

    this.onCalendarioAlterado.emit( { mes : this.mesI+1, ano : this.ano } );
  }

  anterior() {
    if ( this.mesI == 0 ) {
      this.mesI = this.meses.length-1;
      this.ano--;
    } else {
      this.mesI--;
    }
    this.geraCalendario();
  }

  proximo() {
    if ( this.mesI == this.meses.length-1 ) {
      this.mesI = 0;
      this.ano++;
    } else {
      this.mesI++;
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
      this.onDiaClicado.emit( { dia : dia, mes : this.mesI+1, ano : this.ano } );       
    }
  }

  diaQuantidade( i : number, j : number ) {
    let dia =  ((i*7+j+1)-this.primeiroDiaDaSemana);

    if ( this.quantidades !== undefined && this.quantidades !== null ) {
      if ( dia > 0 && dia <= this.quantDiasNoMes ) {
        for( let k = 1; k <= this.quantDiasNoMes; k++ ) {
          for( let kk = 0; kk < this.quantidades.length; kk++ ) {
            if ( this.quantidades[ kk ][ 0 ] == dia ) {
              return this.quantidades[ kk ][ 1 ];
            }
          }
        }
        return 0;
      }
    }
    return '';
  }

}