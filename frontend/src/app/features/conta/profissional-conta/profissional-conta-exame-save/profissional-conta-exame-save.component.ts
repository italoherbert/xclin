import { Component, ViewChild } from '@angular/core';
import { MatInput } from '@angular/material/input';
import { faAdd, faCircleLeft, faRemove, faSave } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalExameVinculoSave } from 'src/app/core/bean/profissional/profissional-exame-vinculo-save';
import { ContaProfissionalService } from 'src/app/core/service/conta-profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { RealInputDirective } from 'src/app/shared/directive/real-input/real-input.directive';

@Component({
  selector: 'app-profissional-conta-exame-save',
  templateUrl: './profissional-conta-exame-save.component.html',
  styleUrls: ['./profissional-conta-exame-save.component.css']
})
export class ProfissionalContaExameSaveComponent {

  @ViewChild( RealInputDirective ) exameValorRealInput! : RealInputDirective;

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRemove : faRemove,
    faAdd : faAdd,
    faCircleLeft : faCircleLeft
  }

  exameSave : ProfissionalExameVinculoSave = {
    exameValor : 0
  }

  exameId : number = 0;

  examesVinculadosIDs : number[] = [];
  examesVinculadosNomes : string[] = [];

  examesNaoVinculadosIDs : number[] = [];
  examesNaoVinculadosNomes : string[] = [];

  constructor(
    private contaProfissionalService : ContaProfissionalService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.atualiza();
  }

  atualiza() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.examesNaoVinculadosIDs.splice( 0, this.examesNaoVinculadosIDs.length );
    this.examesNaoVinculadosNomes.splice( 0, this.examesNaoVinculadosNomes.length );
    this.examesVinculadosIDs.splice( 0, this.examesVinculadosIDs.length );
    this.examesVinculadosNomes.splice( 0, this.examesVinculadosNomes.length );

    this.contaProfissionalService.loadExameVinculoSaveTela().subscribe({
      next: (resp) => {
        for( let i = 0; i < resp.examesIDs.length; i++ ) {
          if ( resp.examesVinculados[ i ] === true ) {
            this.examesVinculadosIDs.push( resp.examesIDs[ i ] );
            this.examesVinculadosNomes.push( resp.examesNomes[ i ] );
          } else {
            this.examesNaoVinculadosIDs.push( resp.examesIDs[ i ] );
            this.examesNaoVinculadosNomes.push( resp.examesNomes[ i ] );
          }
        }

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  salva() {
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.salvaExameVinculo( 
        this.exameId, this.exameSave ).subscribe( {
      next: (resp) => {
        this.infoMsg = "Exame alterado com sucesso.";
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } ); 
  }

  seuExameSelecionado( i : number ) {
    this.exameId = this.examesVinculadosIDs[ i ];

    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.getExameVinculo( this.exameId ).subscribe({
      next: (resp) => {
        this.exameSave.exameValor = resp.exameValor;
        this.exameValorRealInput.setValor( this.exameSave.exameValor );
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  adicionaExame( i : number ) {
    let exameId = this.examesNaoVinculadosIDs[ i ];

    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.vinculaExame( exameId ).subscribe( {
      next: (resp) => {
        this.atualiza();
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  removeExame( i : number ) {
    let exameId = this.examesVinculadosIDs[ i ];
    
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.deletaExameVinculo( exameId ).subscribe( {
      next: (resp) => {
        this.atualiza();
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  onValorExameAlterado( e : any ) {
    this.exameSave.exameValor = e.valorReal
  }

}
