import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfissionalContaExameSaveComponent } from './profissional-conta-exame-save.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatChipsModule } from '@angular/material/chips';
import { RealInputModule } from 'src/app/shared/directive/real-input/real-input.module';



@NgModule({
  declarations: [
    ProfissionalContaExameSaveComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    RealInputModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatChipsModule
  ]
})
export class ProfissionalContaExameSaveModule { }
