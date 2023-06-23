import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPlus, faPlusCircle, faSave, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { AnamneseModeloPergunta } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo-pergunta';
import { AnamneseModeloPerguntaSave } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo-pergunta-save';
import { AnamenseModeloPerguntaService } from 'src/app/core/service/anamense-modelo-pergunta.service';
import { AnamneseModeloService } from 'src/app/core/service/anamnese-modelo.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-anamnese-modelo-pergunta-save',
  templateUrl: './anamnese-modelo-pergunta-save.component.html',
  styleUrls: ['./anamnese-modelo-pergunta-save.component.css']
})
export class AnamneseModeloPerguntaSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPlus : faPlus,
    faTrashCan : faTrashCan,
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  perguntaSave : AnamneseModeloPerguntaSave = {
    pergunta: '',
    tipo: '',
    enums: ''
  }

  perguntas : AnamneseModeloPergunta = {
    id: 0,
    pergunta: '',
    tipo: '',
    tipoLabel: '',
    enums: ''
  }

  perguntaTipos : any[] = [];

  enums : string[] = [];
  enum : string = '';

  anamneseModeloId : any = -1;

  constructor(
    private actRoute : ActivatedRoute,
    private anamneseModeloPerguntaService : AnamenseModeloPerguntaService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let perguntaId = this.actRoute.snapshot.paramMap.get( 'perguntaId' );
    let modeloId = this.actRoute.snapshot.paramMap.get( 'modeloId' );

    this.anamneseModeloId = modeloId;

    if ( perguntaId == '-1' ) {
      this.anamneseModeloPerguntaService.loadRegTela().subscribe( {
        next: (resp) => {
          this.perguntaTipos = resp.perguntaTipos;

          if ( this.perguntaTipos.length > 0 )
            this.perguntaSave.tipo = this.perguntaTipos[ 0 ].name;

          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    } else {
      this.anamneseModeloPerguntaService.loadEditTela( perguntaId ).subscribe({
        next: (resp) => {
          this.perguntaTipos = resp.perguntaTipos;
          this.perguntaSave = resp.pergunta;
          this.enums = resp.pergunta.enums.split( ',' );
          this.enum = '';
          this.showSpinner = false;
        },
        error: (erro) =>{
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

  addEnum() {
    this.enums.push( this.enum );
    this.enum = '';
  }

  removeEnum( i : number ) {
    this.enums.splice( i, 1 );
  }
  
  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = false;

    if ( this.perguntaSave.tipo == 'ENUM' ) {
      this.perguntaSave.enums = '';
      for( let i = 0; i < this.enums.length; i++ ) {
        this.perguntaSave.enums += this.enums[ i ];
        if ( i < this.enums.length-1 )
          this.perguntaSave.enums += ',';
      }
    }

    let perguntaId = this.actRoute.snapshot.paramMap.get( 'perguntaId' );
    if ( perguntaId == '-1' ) {
      let modeloId = this.actRoute.snapshot.paramMap.get( 'modeloId' );

      this.anamneseModeloPerguntaService.registra( modeloId, this.perguntaSave ).subscribe({
        next: (resp) => {
          this.limpaForm();
          this.infoMsg = "Pergunta registrada com sucesso.";
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.anamneseModeloPerguntaService.altera( perguntaId, this.perguntaSave ).subscribe({
        next: (resp) => {
          this.infoMsg = "Pergunta salva com sucesso.";
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

  limpaForm() {
    this.perguntaSave.pergunta = '';

    if ( this.perguntaTipos.length > 0 )
      this.perguntaSave.tipo = this.perguntaTipos[ 0 ].name;

    this.perguntaSave.enums = '';

    this.enum = '';
    this.enums.splice( 0, this.enums.length );
  }

}
