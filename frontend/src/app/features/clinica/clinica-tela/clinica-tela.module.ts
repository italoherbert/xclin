import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClinicaRemoveDialog, ClinicaTelaComponent } from './clinica-tela.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';



@NgModule({
  declarations: [
    ClinicaTelaComponent,
    ClinicaRemoveDialog
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
export class ClinicaTelaModule { }
