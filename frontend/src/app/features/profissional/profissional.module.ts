import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NaoAdminProfissionalTelaModule } from './nao-admin-profissional-tela/nao-admin-profissional-tela.module';
import { ProfissionalDetalhesModule } from './profissional-detalhes/profissional-detalhes.module';
import { ProfissionalSaveModule } from './profissional-save/profissional-save.module';
import { ProfissionalTelaModule } from './profissional-tela/profissional-tela.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    NaoAdminProfissionalTelaModule,
    ProfissionalTelaModule,
    ProfissionalDetalhesModule,
    ProfissionalSaveModule
  ]
})
export class ProfissionalModule { }
