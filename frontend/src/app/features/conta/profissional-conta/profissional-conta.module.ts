import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfissionalContaAlterarModule } from './profissional-conta-alterar/profissional-conta-alterar.module';
import { ProfissionalContaDetalhesModule } from './profissional-conta-detalhes/profissional-conta-detalhes.module';
import { ProfissionalContaEspecialidadeSaveModule } from './profissional-conta-especialidade-save/profissional-conta-especialidade-save.module';
import { ProfissionalContaEspecialidadesModule } from './profissional-conta-especialidades/profissional-conta-especialidades.module';
import { ProfissionalContaExamesModule } from './profissional-conta-exames/profissional-conta-exames.module';
import { ProfissionalContaExameSaveModule } from './profissional-conta-exame-save/profissional-conta-exame-save.module';
import { ProfissionalContaProcedimentosModule } from './profissional-conta-procedimentos/profissional-conta-procedimentos.module';
import { ProfissionalContaProcedimentoSaveModule } from './profissional-conta-procedimento-save/profissional-conta-procedimento-save.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    ProfissionalContaAlterarModule,
    ProfissionalContaDetalhesModule,
    
    ProfissionalContaEspecialidadeSaveModule,
    ProfissionalContaEspecialidadesModule,

    ProfissionalContaExamesModule,
    ProfissionalContaExameSaveModule,

    ProfissionalContaProcedimentosModule,
    ProfissionalContaProcedimentoSaveModule
  ]
})
export class ProfissionalContaModule { }
