import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LancamentoTelaModule } from './lancamento-tela/lancamento-tela.module';
import { LancamentoNovoModule } from './lancamento-novo/lancamento-novo.module';
import { LancamentoDetalhesModule } from './lancamento-detalhes/lancamento-detalhes.module';


@NgModule({
  declarations: [
    
  ],
  imports: [
    CommonModule,

    LancamentoTelaModule,
    LancamentoNovoModule,
    LancamentoDetalhesModule
  ]
})
export class LancamentoModule { }
