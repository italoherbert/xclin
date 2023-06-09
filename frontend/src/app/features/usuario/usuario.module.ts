import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioTelaModule } from './usuario-tela/usuario-tela.module';
import { UsuarioDetalhesModule } from './usuario-detalhes/usuario-detalhes.module';
import { UsuarioSaveModule } from './usuario-save/usuario-save.module';
import { UsuarioGrupoVinculosModule } from './usuario-grupo-vinculos/usuario-grupo-vinculos.module';



@NgModule({
  declarations: [    
  ],
  imports: [
    CommonModule,

    UsuarioTelaModule,
    UsuarioDetalhesModule,
    UsuarioSaveModule,
    UsuarioGrupoVinculosModule
  ]
})
export class UsuarioModule { }
