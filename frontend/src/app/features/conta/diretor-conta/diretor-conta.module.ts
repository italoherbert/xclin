import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DiretorContaAlterarModule } from './diretor-conta-alterar/diretor-conta-alterar.module';
import { DiretorContaDetalhesModule } from './diretor-conta-detalhes/diretor-conta-detalhes.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    DiretorContaAlterarModule,
    DiretorContaDetalhesModule
  ]
})
export class DiretorContaModule { }
