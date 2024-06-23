import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioGrupoAcessosComponent } from './usuario-grupo-acessos.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTableModule } from '@angular/material/table';
import { PaginatorModule } from 'src/app/shared/paginator/paginator.module';


@NgModule({
  declarations: [
    UsuarioGrupoAcessosComponent
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatCardModule,
    MatButtonModule,
    MatCheckboxModule,
    MatTableModule,

    PaginatorModule
  ]
})
export class UsuarioGrupoAcessosModule { }
