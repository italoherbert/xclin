import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProcedimentoRemoveDialog, ProcedimentoTelaComponent } from './procedimento-tela.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { PaginatorModule } from 'src/app/shared/paginator/paginator.module';



@NgModule({
  declarations: [
    ProcedimentoTelaComponent,
    ProcedimentoRemoveDialog
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule,
    MatDialogModule,
    MatTableModule,

    PaginatorModule
  ]
})
export class ProcedimentoTelaModule { }
