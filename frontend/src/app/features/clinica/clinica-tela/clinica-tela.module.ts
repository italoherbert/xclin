import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClinicaRemoveDialog, ClinicaTelaComponent } from './clinica-tela.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { PaginatorModule } from 'src/app/shared/paginator/paginator.module';



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
    MatDialogModule,
    MatTableModule,

    PaginatorModule
  ]
})
export class ClinicaTelaModule { }
