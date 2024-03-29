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
    MatDialogModule
  ]
})
export class ProcedimentoTelaModule { }
