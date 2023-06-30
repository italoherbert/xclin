import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Exame } from 'src/app/core/bean/exame/exame';
import { ExameService } from 'src/app/core/service/exame.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-exame-detalhes',
  templateUrl: './exame-detalhes.component.html',
  styleUrls: ['./exame-detalhes.component.css']
})
export class ExameDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  exame : Exame = {
    id : 0,
    nome: '', 
    descricao: '',
    valor : 0
  }

  constructor( 
    private actRoute : ActivatedRoute, 
    private exameService: ExameService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let exameId = this.actRoute.snapshot.paramMap.get( 'exameId' );

    this.exameService.get( exameId ).subscribe({
      next: ( resp ) => {
        this.exame = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }


}
