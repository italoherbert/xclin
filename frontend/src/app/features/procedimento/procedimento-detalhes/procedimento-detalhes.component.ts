import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Procedimento } from 'src/app/core/bean/procedimento/procedimento';
import { ProcedimentoService } from 'src/app/core/service/procedimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-procedimento-detalhes',
  templateUrl: './procedimento-detalhes.component.html',
  styleUrls: ['./procedimento-detalhes.component.css']
})
export class ProcedimentoDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  procedimento : Procedimento = {
    id : 0,
    nome: '', 
    descricao: ''
  }

  constructor( 
    private actRoute : ActivatedRoute, 
    private procedimentoService: ProcedimentoService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let procedimentoId = this.actRoute.snapshot.paramMap.get( 'procedimentoId' );

    this.procedimentoService.get( procedimentoId ).subscribe({
      next: ( resp ) => {
        this.procedimento = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}

