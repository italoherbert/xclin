import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { AnamneseModeloPergunta } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo-pergunta';
import { AnamneseModeloPerguntaSave } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo-pergunta-save';
import { AnamneseModeloService } from 'src/app/core/service/anamnese-modelo.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-anamnese-modelo-perguntas',
  templateUrl: './anamnese-modelo-perguntas.component.html',
  styleUrls: ['./anamnese-modelo-perguntas.component.css']
})
export class AnamneseModeloPerguntasComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    
  }

  perguntaSave : AnamneseModeloPerguntaSave = {
    pergunta: '',
    tipo: '',
    enumNames: '',
    enumValues: ''
  }

  perguntas : AnamneseModeloPergunta = {
    id: 0,
    pergunta: '',
    tipo: '',
    tipoLabel: '',
    enumNames: '',
    enumValues: ''
  }

  perguntaTipos : any[] = [];

  constructor(
    private actRoute : ActivatedRoute,
    private anamneseModeloService : AnamneseModeloService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.anamneseModeloService.perguntasTelaLoad( id ).subscribe( {
      next: (resp) => {
        this.perguntaTipos = resp.perguntaTipos;
        this.perguntas = resp.perguntas;

        if ( this.perguntaTipos.length > 0 )
          this.perguntaSave.tipo = this.perguntaTipos[ 0 ].name;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

}
