import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioGrupoRemoveDialog, UsuarioGrupoTelaComponent } from './usuario-grupo-tela.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';



@NgModule({
  declarations: [
    UsuarioGrupoTelaComponent,
    UsuarioGrupoRemoveDialog
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule
  ]
})
export class UsuarioGrupoTelaModule { }
