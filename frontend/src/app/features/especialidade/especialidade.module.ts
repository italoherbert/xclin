import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EspecialidadeDetalhesModule } from './especialidade-detalhes/especialidade-detalhes.module';
import { EspecialidadeSaveModule } from './especialidade-save/especialidade-save.module';
import { EspecialidadeTelaModule } from './especialidade-tela/especialidade-tela.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    EspecialidadeDetalhesModule,
    EspecialidadeSaveModule,
    EspecialidadeTelaModule
  ]
})
export class EspecialidadeModule { }
