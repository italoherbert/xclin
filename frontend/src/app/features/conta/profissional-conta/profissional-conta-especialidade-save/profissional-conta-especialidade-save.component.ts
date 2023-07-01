import { Component, ViewChild } from '@angular/core';
import { faAdd, faCircleLeft, faRemove, faSave } from '@fortawesome/free-solid-svg-icons';

import { ProfissionalEspecialidadeVinculoSave } from 'src/app/core/bean/profissional/profissional-especialidade-vinculo-save';
import { ContaProfissionalService } from 'src/app/core/service/conta-profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { RealInputDirective } from 'src/app/shared/directive/real-input/real-input.directive';

@Component({
  selector: 'app-profissional-conta-especialidade-save',
  templateUrl: './profissional-conta-especialidade-save.component.html',
  styleUrls: ['./profissional-conta-especialidade-save.component.css']
})
export class ProfissionalContaEspecialidadeSaveComponent {

  @ViewChild( RealInputDirective ) consultaValorRealInput! : RealInputDirective;


  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRemove : faRemove,
    faAdd : faAdd,
    faCircleLeft : faCircleLeft
  }

  especialidadeSave : ProfissionalEspecialidadeVinculoSave = {
    consultaValor : 0
  }

  especialidadeId : number = 0;

  especialidadesVinculadasIDs : number[] = [];
  especialidadesVinculadasNomes : string[] = [];

  especialidadesNaoVinculadasIDs : number[] = [];
  especialidadesNaoVinculadasNomes : string[] = [];

  constructor( 
    private contaProfissionalService: ContaProfissionalService,
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.atualiza();
  }

  atualiza() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = false;

    this.especialidadesNaoVinculadasIDs.splice( 0, this.especialidadesNaoVinculadasIDs.length );
    this.especialidadesNaoVinculadasNomes.splice( 0, this.especialidadesNaoVinculadasNomes.length );
    this.especialidadesVinculadasIDs.splice( 0, this.especialidadesVinculadasIDs.length );
    this.especialidadesVinculadasNomes.splice( 0, this.especialidadesVinculadasNomes.length );

    this.contaProfissionalService.loadEspecialidadeVinculoSaveTela().subscribe({
      next: (resp) => {
        for( let i = 0; i < resp.especialidadesIDs.length; i++ ) {
          if ( resp.especialidadesVinculadas[ i ] === true ) {
            this.especialidadesVinculadasIDs.push( resp.especialidadesIDs[ i ] );
            this.especialidadesVinculadasNomes.push( resp.especialidadesNomes[ i ] );
          } else {
            this.especialidadesNaoVinculadasIDs.push( resp.especialidadesIDs[ i ] );
            this.especialidadesNaoVinculadasNomes.push( resp.especialidadesNomes[ i ] );
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

    this.contaProfissionalService.salvaEspecialidadeVinculo( 
        this.especialidadeId, this.especialidadeSave ).subscribe( {
      next: (resp) => {
        this.infoMsg = "Especialidade alterada com sucesso.";
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } ); 
  }

  suaEspecialidadeSelecionada( i : number ) {
    this.especialidadeId = this.especialidadesVinculadasIDs[ i ];

    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.getEspecialidadeVinculo( this.especialidadeId ).subscribe({
      next: (resp) => {
        this.consultaValorRealInput.setValor( resp.consultaValor );
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  adicionaEspecialidade( i : number ) {
    let especialidadeId = this.especialidadesNaoVinculadasIDs[ i ];

    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.vinculaEspecialidade( especialidadeId ).subscribe( {
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

  removeEspecialidade( i : number ) {    
    let especialidadeId = this.especialidadesVinculadasIDs[ i ];
    
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.deletaEspecialidadeVinculo( especialidadeId ).subscribe( {
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

  onValorConsultaAlterado( e : any ) {    
    this.especialidadeSave.consultaValor = e.valorReal;
  }

}
