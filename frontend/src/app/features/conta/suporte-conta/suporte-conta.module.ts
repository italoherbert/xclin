import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SuporteContaDetalhesModule } from './suporte-conta-detalhes/suporte-conta-detalhes.module';
import { SuporteContaAlterarModule } from './suporte-conta-alterar/suporte-conta-alterar.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    SuporteContaDetalhesModule,
    SuporteContaAlterarModule
  ]
})
export class SuporteContaModule { }
