import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfissionalContaProcedimentoSaveComponent } from './profissional-conta-procedimento-save.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { RealInputModule } from 'src/app/shared/directive/real-input/real-input.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatChipsModule } from '@angular/material/chips';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    ProfissionalContaProcedimentoSaveComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    RealInputModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatChipsModule,
    MatButtonModule
  ]
})
export class ProfissionalContaProcedimentoSaveModule { }
