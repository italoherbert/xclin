import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faEye, faFilter, faPlus, faPlusCircle, faSave, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Lancamento } from 'src/app/core/bean/lancamento/lancamento';
import { LancamentoFiltro } from 'src/app/core/bean/lancamento/lancamento-filtro';
import { LancamentoService } from 'src/app/core/service/lancamento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

import * as moment from 'moment';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-lancamento-tela',
  templateUrl: './lancamento-tela.component.html',
  styleUrls: ['./lancamento-tela.component.css']
})
export class LancamentoTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faFilter : faFilter,
    faPlusCircle : faPlusCircle,
    faTrashCan : faTrashCan,
    faEye: faEye
  }

  lancamentoFiltro : LancamentoFiltro = {
    dataInicio : '',
    dataFim : '',
    incluirUsername : false,
    filtroUsername : ''
  }

  saldos : number[] = [];

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  clinicaId : number = 0;

  lancamentosColumns: string[] = ['dataLancamento', 'tipo', 'valor', 'saldo', 'detalhes', 'remover'];
  lancamentosDataSource = new MatTableDataSource<Lancamento>([]);

  constructor(
    private matDialog : MatDialog,
    private lancamentoService: LancamentoService,
    private sistemaService: SistemaService
  ){}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.lancamentoFiltro.dataInicio = moment().format();
    this.lancamentoFiltro.dataFim = moment().format();

    this.lancamentoService.getTelaLoad().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;

        if ( this.clinicasIDs.length > 0 )
          this.clinicaId = this.clinicasIDs[ 0 ];

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.lancamentoService.filtra( this.clinicaId, this.lancamentoFiltro ).subscribe({
      next: (resp) => {
        this.lancamentosDataSource.data = resp;

        this.saldos.splice( 0, this.saldos.length );

        let saldo = 0;
        for( let i = 0; i < this.lancamentosDataSource.data.length; i++ ) {
          let l = this.lancamentosDataSource.data[ i ];
          if ( l.tipo === 'CREDITO' ) {
            saldo += l.valor;
          } else if ( l.tipo == 'DEBITO' ) {
            saldo -= l.valor;
          }           
          this.saldos.push( saldo );
        }
        
        if ( this.lancamentosDataSource.data.length === 0 )
          this.infoMsg = "Nenhum lançamento encontrado pelos critérios de busca informados.";
          
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;      
      }
    });
  }

  remove( id : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.lancamentoService.deleta( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Lancamento deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraRemoveDialog( id : any ) {
    let dialogRef = this.matDialog.open( LancamentoRemoveDialog );
    dialogRef.afterClosed().subscribe( ( result ) => {
      if ( result === true )
        this.remove( id );
    } );
  }

}

@Component({
  selector: "lancamento-remove-dialog",
  templateUrl: "lancamento-remove-dialog.html"
})
export class LancamentoRemoveDialog {

}

