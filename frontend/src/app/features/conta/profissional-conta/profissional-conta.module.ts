import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfissionalContaAlterarModule } from './profissional-conta-alterar/profissional-conta-alterar.module';
import { ProfissionalContaDetalhesModule } from './profissional-conta-detalhes/profissional-conta-detalhes.module';
import { ProfissionalContaEspecialidadeSaveModule } from './profissional-conta-especialidade-save/profissional-conta-especialidade-save.module';
import { ProfissionalContaEspecialidadesModule } from './profissional-conta-especialidades/profissional-conta-especialidades.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    ProfissionalContaAlterarModule,
    ProfissionalContaDetalhesModule,
    ProfissionalContaEspecialidadeSaveModule,
    ProfissionalContaEspecialidadesModule
  ]
})
export class ProfissionalContaModule { }
