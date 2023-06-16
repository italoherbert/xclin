import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DiretorSaveModule } from './diretor-save/diretor-save.module';
import { DiretorDetalhesModule } from './diretor-detalhes/diretor-detalhes.module';
import { DiretorTelaModule } from './diretor-tela/diretor-tela.module';
import { NaoAdminDiretorTelaModule } from './nao-admin-diretor-tela/nao-admin-diretor-tela.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    DiretorDetalhesModule,
    DiretorSaveModule,
    DiretorTelaModule,
    NaoAdminDiretorTelaModule
  ]
})
export class DiretorModule { }
