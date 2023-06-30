import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExameSaveModule } from './exame-save/exame-save.module';
import { ExameDetalhesModule } from './exame-detalhes/exame-detalhes.module';
import { ExameTelaModule } from './exame-tela/exame-tela.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    ExameTelaModule,
    ExameDetalhesModule,
    ExameSaveModule
  ]
})
export class ExameModule { }
