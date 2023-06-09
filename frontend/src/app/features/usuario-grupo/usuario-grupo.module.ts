import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioGrupoAcessosModule } from './usuario-grupo-acessos/usuario-grupo-acessos.module';
import { UsuarioGrupoDetalhesModule } from './usuario-grupo-detalhes/usuario-grupo-detalhes.module';
import { UsuarioGrupoSaveModule } from './usuario-grupo-save/usuario-grupo-save.module';
import { UsuarioGrupoTelaModule } from './usuario-grupo-tela/usuario-grupo-tela.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    UsuarioGrupoAcessosModule,
    UsuarioGrupoDetalhesModule,
    UsuarioGrupoSaveModule,
    UsuarioGrupoTelaModule
  ]
})
export class UsuarioGrupoModule { }
