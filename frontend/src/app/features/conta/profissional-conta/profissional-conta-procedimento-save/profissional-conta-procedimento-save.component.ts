import { Component, ViewChild } from '@angular/core';
import { faAdd, faCircleLeft, faRemove, faSave } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalProcedimentoVinculoSave } from 'src/app/core/bean/profissional/profissional-procedimento-vinculo-save';
import { ContaProfissionalService } from 'src/app/core/service/conta-profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { RealInputDirective } from 'src/app/shared/directive/real-input/real-input.directive';

@Component({
  selector: 'app-profissional-conta-procedimento-save',
  templateUrl: './profissional-conta-procedimento-save.component.html',
  styleUrls: ['./profissional-conta-procedimento-save.component.css']
})
export class ProfissionalContaProcedimentoSaveComponent {

  @ViewChild( RealInputDirective ) procedimentoValorRealInput! : RealInputDirective;

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRemove : faRemove,
    faAdd : faAdd,
    faCircleLeft : faCircleLeft
  }

  procedimentoSave : ProfissionalProcedimentoVinculoSave = {
    procedimentoValor : 0
  }

  procedimentoId : number = 0;

  procedimentosVinculadosIDs : number[] = [];
  procedimentosVinculadosNomes : string[] = [];

  procedimentosNaoVinculadosIDs : number[] = [];
  procedimentosNaoVinculadosNomes : string[] = [];

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

    this.procedimentosNaoVinculadosIDs.splice( 0, this.procedimentosNaoVinculadosIDs.length );
    this.procedimentosNaoVinculadosNomes.splice( 0, this.procedimentosNaoVinculadosNomes.length );
    this.procedimentosVinculadosIDs.splice( 0, this.procedimentosVinculadosIDs.length );
    this.procedimentosVinculadosNomes.splice( 0, this.procedimentosVinculadosNomes.length );

    this.contaProfissionalService.loadProcedimentoVinculoSaveTela().subscribe({
      next: (resp) => {
        for( let i = 0; i < resp.procedimentosIDs.length; i++ ) {
          if ( resp.procedimentosVinculados[ i ] === true ) {
            this.procedimentosVinculadosIDs.push( resp.procedimentosIDs[ i ] );
            this.procedimentosVinculadosNomes.push( resp.procedimentosNomes[ i ] );
          } else {
            this.procedimentosNaoVinculadosIDs.push( resp.procedimentosIDs[ i ] );
            this.procedimentosNaoVinculadosNomes.push( resp.procedimentosNomes[ i ] );
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

    this.contaProfissionalService.salvaProcedimentoVinculo( 
        this.procedimentoId, this.procedimentoSave ).subscribe( {
      next: (resp) => {
        this.infoMsg = "Procedimento alterado com sucesso.";
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } ); 
  }

  seuProcedimentoSelecionado( i : number ) {
    this.procedimentoId = this.procedimentosVinculadosIDs[ i ];

    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.getProcedimentoVinculo( this.procedimentoId ).subscribe({
      next: (resp) => {
        this.procedimentoSave.procedimentoValor = resp.procedimentoValor;
        this.procedimentoValorRealInput.setValor( this.procedimentoSave.procedimentoValor );
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  adicionaProcedimento( i : number ) {
    let procedimentoId = this.procedimentosNaoVinculadosIDs[ i ];

    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.vinculaProcedimento( procedimentoId ).subscribe( {
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

  removeProcedimento( i : number ) {
    let procedimentoId = this.procedimentosVinculadosIDs[ i ];
    
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.deletaProcedimentoVinculo( procedimentoId ).subscribe( {
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

  onValorProcedimentoAlterado( e : any ) {
    this.procedimentoSave.procedimentoValor = e.valorReal
  }

}