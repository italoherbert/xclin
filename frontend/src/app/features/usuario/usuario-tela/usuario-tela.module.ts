import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { UsuarioRemoveDialog, UsuarioTelaComponent } from './usuario-tela.component';
import { MatDialogModule } from '@angular/material/dialog';



@NgModule({
  declarations: [
    UsuarioTelaComponent,
    UsuarioRemoveDialog
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule
  ]
})
export class UsuarioTelaModule { }
