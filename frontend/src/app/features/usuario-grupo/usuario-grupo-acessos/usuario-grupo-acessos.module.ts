import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioGrupoAcessosComponent } from './usuario-grupo-acessos.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';


@NgModule({
  declarations: [
    UsuarioGrupoAcessosComponent
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatCardModule,
    MatButtonModule,
    MatCheckboxModule
  ]
})
export class UsuarioGrupoAcessosModule { }
