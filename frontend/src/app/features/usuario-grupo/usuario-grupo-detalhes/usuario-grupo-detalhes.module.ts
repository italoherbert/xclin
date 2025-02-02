import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioGrupoDetalhesComponent } from './usuario-grupo-detalhes.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTableModule } from '@angular/material/table';
import { PaginatorModule } from 'src/app/shared/paginator/paginator.module';

@NgModule({
  declarations: [
    UsuarioGrupoDetalhesComponent
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
export class UsuarioGrupoDetalhesModule { }
