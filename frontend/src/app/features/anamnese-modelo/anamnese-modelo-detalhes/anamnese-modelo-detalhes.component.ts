import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faAngleDown, faCircleLeft, faPenToSquare, faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { AnamneseModelo } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo';
import { AnamneseModeloPergunta } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo-pergunta';
import { AnamenseModeloPerguntaService } from 'src/app/core/service/anamense-modelo-pergunta.service';
import { AnamneseModeloService } from 'src/app/core/service/anamnese-modelo.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-anamnese-modelo-detalhes',
  templateUrl: './anamnese-modelo-detalhes.component.html',
  styleUrls: ['./anamnese-modelo-detalhes.component.css']
})
export class AnamneseModeloDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft,
    faPlusCircle : faPlusCircle,
    faAngleDown : faAngleDown
  }

  modelo : AnamneseModelo = {
    id: 0,
    nome: ''
  }

  perguntas : AnamneseModeloPergunta[] = [];

  constructor(
    private actRoute : ActivatedRoute,
    private anamneseModeloService : AnamneseModeloService,
    private anamneseModeloPerguntaService : AnamenseModeloPerguntaService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.carrega();
  }

  carrega() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.anamneseModeloService.loadDetalhes( id ).subscribe({
      next: (resp) => {
        this.modelo = resp.anamneseModelo;
        this.perguntas = resp.perguntas;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  removePergunta( id : any ) {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    this.anamneseModeloPerguntaService.deleta( id ).subscribe({
      next: (resp) => {
        this.carrega();
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
