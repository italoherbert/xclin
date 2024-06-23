import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PacienteRemoveDialog, PacienteTelaComponent } from './paciente-tela.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { PaginatorModule } from 'src/app/shared/paginator/paginator.module';



@NgModule({
  declarations: [
    PacienteTelaComponent, 
    PacienteRemoveDialog
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    MatSelectModule,
    MatOptionModule,
    MatTableModule,

    PaginatorModule
  ]
})
export class PacienteTelaModule { }
