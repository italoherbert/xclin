import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecepcionistaContaAlterarModule } from './recepcionista-conta-alterar/recepcionista-conta-alterar.module';
import { RecepcionistaContaDetalhesModule } from './recepcionista-conta-detalhes/recepcionista-conta-detalhes.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    RecepcionistaContaAlterarModule,
    RecepcionistaContaDetalhesModule
  ]
})
export class RecepcionistaContaModule { }
