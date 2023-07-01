import { Component } from '@angular/core';
import { faAdd, faCircleLeft, faRemove, faSave } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalExameVinculoSave } from 'src/app/core/bean/profissional/profissional-exame-vinculo-save';
import { ContaProfissionalService } from 'src/app/core/service/conta-profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-profissional-conta-exame-save',
  templateUrl: './profissional-conta-exame-save.component.html',
  styleUrls: ['./profissional-conta-exame-save.component.css']
})
export class ProfissionalContaExameSaveComponent {

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

  }

  seuExameSelecionado( i : number ) {

  }

  adicionaExame( i : number ) {

  }

  removeExame( i : number ) {

  }

  onValorExameAlterado( e : any ) {
    this.exameSave.exameValor = e.valorReal
  }

}
