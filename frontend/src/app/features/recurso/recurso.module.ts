import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecursoDetalhesModule } from './recurso-detalhes/recurso-detalhes.module';
import { RecursoSaveModule } from './recurso-save/recurso-save.module';
import { RecursoTelaModule } from './recurso-tela/recurso-tela.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    RecursoDetalhesModule,
    RecursoSaveModule,
    RecursoTelaModule
  ]
})
export class RecursoModule { }
