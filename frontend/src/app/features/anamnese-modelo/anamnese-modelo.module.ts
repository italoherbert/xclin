import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnamneseModeloTelaModule } from './anamnese-modelo-tela/anamnese-modelo-tela.module';
import { AnamneseModeloSaveModule } from './anamnese-modelo-save/anamnese-modelo-save.module';
import { AnamneseModeloDetalhesModule } from './anamnese-modelo-detalhes/anamnese-modelo-detalhes.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    AnamneseModeloTelaModule,
    AnamneseModeloSaveModule,
    AnamneseModeloDetalhesModule
  ]
})
export class AnamneseModeloModule { }
