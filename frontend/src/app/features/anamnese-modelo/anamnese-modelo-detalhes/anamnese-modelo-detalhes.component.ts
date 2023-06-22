import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare, faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { AnamneseModelo } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo';
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
    faPlusCircle : faPlusCircle
  }

  modelo : AnamneseModelo = {
    id: 0,
    nome: ''
  }

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

    this.anamneseModeloService.get( id ).subscribe({
      next: (resp) => {
        this.modelo = resp;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
