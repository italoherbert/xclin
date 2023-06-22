import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft } from '@fortawesome/free-solid-svg-icons';
import { Lancamento } from 'src/app/core/bean/lancamento/lancamento';
import { LancamentoService } from 'src/app/core/service/lancamento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-lancamento-detalhes',
  templateUrl: './lancamento-detalhes.component.html',
  styleUrls: ['./lancamento-detalhes.component.css']
})
export class LancamentoDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleLeft : faCircleLeft
  }

  lancamento : Lancamento = {
    id: 0,
    valor: 0,
    tipo: '',
    tipoLabel: '',
    dataLancamento: '',
    observacoes: '',
    clinicaId: 0,
    clinicaNome: '',
    usuarioId: 0,
    usuarioUsername: ''
  }

  constructor( 
    private actRoute: ActivatedRoute,
    private lancamentoService: LancamentoService, 
    private sistemaService: SistemaService ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let lancamentoId = this.actRoute.snapshot.paramMap.get( 'lancamentoId' );

    this.lancamentoService.get( lancamentoId ).subscribe( {
      next: (resp) => {
        this.lancamento = resp;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

}
