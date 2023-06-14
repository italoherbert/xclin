import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LancamentoTelaModule } from './lancamento-tela/lancamento-tela.module';
import { LancamentoNovoModule } from './lancamento-novo/lancamento-novo.module';


@NgModule({
  declarations: [
    
  ],
  imports: [
    CommonModule,

    LancamentoTelaModule,
    LancamentoNovoModule
  ]
})
export class LancamentoModule { }
