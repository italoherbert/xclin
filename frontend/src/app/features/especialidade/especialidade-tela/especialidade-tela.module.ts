import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { EspecialidadeRemoveDialog, EspecialidadeTelaComponent } from './especialidade-tela.component';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    EspecialidadeTelaComponent,
    EspecialidadeRemoveDialog
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
export class EspecialidadeTelaModule { }
