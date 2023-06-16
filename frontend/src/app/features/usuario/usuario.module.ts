import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioTelaModule } from './usuario-tela/usuario-tela.module';
import { UsuarioDetalhesModule } from './usuario-detalhes/usuario-detalhes.module';
import { UsuarioSaveModule } from './usuario-save/usuario-save.module';
import { UsuarioGrupoVinculosModule } from './usuario-grupo-vinculos/usuario-grupo-vinculos.module';
import { UsuarioClinicaVinculosModule } from './usuario-clinica-vinculos/usuario-clinica-vinculos.module';



@NgModule({
  declarations: [    
  ],
  imports: [
    CommonModule,

    UsuarioTelaModule,
    UsuarioDetalhesModule,
    UsuarioSaveModule,
    UsuarioGrupoVinculosModule,
    UsuarioClinicaVinculosModule
  ]
})
export class UsuarioModule { }
