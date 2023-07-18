import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfissionalContaProcedimentosComponent } from './profissional-conta-procedimentos.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';



@NgModule({
  declarations: [
    ProfissionalContaProcedimentosComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule
  ]
})
export class ProfissionalContaProcedimentosModule { }
