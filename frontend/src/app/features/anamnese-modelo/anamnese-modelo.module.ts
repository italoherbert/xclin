import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnamneseModeloTelaModule } from './anamnese-modelo-tela/anamnese-modelo-tela.module';
import { AnamneseModeloSaveModule } from './anamnese-modelo-save/anamnese-modelo-save.module';
import { AnamneseModeloDetalhesModule } from './anamnese-modelo-detalhes/anamnese-modelo-detalhes.module';
import { AnamneseModeloPerguntaSaveModule } from './anamnese-modelo-pergunta-save/anamnese-modelo-pergunta-save.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    AnamneseModeloTelaModule,
    AnamneseModeloSaveModule,
    AnamneseModeloDetalhesModule,
    AnamneseModeloPerguntaSaveModule
  ]
})
export class AnamneseModeloModule { }
