import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProcedimentoTelaModule } from './procedimento-tela/procedimento-tela.module';
import { ProcedimentoDetalhesModule } from './procedimento-detalhes/procedimento-detalhes.module';
import { ProcedimentoSaveModule } from './procedimento-save/procedimento-save.module';



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    
    ProcedimentoTelaModule,
    ProcedimentoDetalhesModule,
    ProcedimentoSaveModule
  ]
})
export class ProcedimentoModule { }
