import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faMoneyBill1 } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoPagamento } from 'src/app/core/bean/atendimento/atendimento-pagamento';
import { AtendimentoPagamentoSave } from 'src/app/core/bean/atendimento/atendimento-pagamento-save';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-atendimento-pagamento',
  templateUrl: './atendimento-pagamento.component.html',
  styleUrls: ['./atendimento-pagamento.component.css']
})
export class AtendimentoPagamentoComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faMoneyBill1 : faMoneyBill1
  }

  pagamento : AtendimentoPagamento = {
    pago : false,
    valorTotal : 0,
    valorPago : 0
  }

  pagamentoSave : AtendimentoPagamentoSave = {
    valorPago: 0
  }

  constructor(
    private actRoute : ActivatedRoute,
    private atendimentoService : AtendimentoService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.carrega();
  }

  carrega() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let atendimentoId = this.actRoute.snapshot.paramMap.get( 'atendimentoId' );

    this.atendimentoService.loadPagamentoTela( atendimentoId ).subscribe({
      next: (resp) => {
        this.pagamento = resp;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  efetuaPagamento() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let atendimentoId = this.actRoute.snapshot.paramMap.get( 'atendimentoId' );

    this.atendimentoService.efetuaPagamento( atendimentoId, this.pagamentoSave ).subscribe({
      next: (resp) => {
        this.showSpinner = false;
        this.carrega();
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  desfazPagamento() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let atendimentoId = this.actRoute.snapshot.paramMap.get( 'atendimentoId' );

    this.atendimentoService.desfazPagamento( atendimentoId ).subscribe({
      next: (resp) => {
        this.showSpinner = false;
        this.carrega();
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  onPagamentoValorAlterado( e : any ) {
    this.pagamentoSave.valorPago = e.valorReal;
  }

}
