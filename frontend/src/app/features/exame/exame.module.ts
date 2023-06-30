import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExameDetalhesModule } from './exame-detalhes/exame-detalhes.module';
import { ExameTelaModule } from './exame-tela/exame-tela.module';
import { ExameNovoModule } from './exame-novo/exame-novo.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    ExameNovoModule,
    ExameDetalhesModule,
    ExameTelaModule
  ]
})
export class ExameModule { }
