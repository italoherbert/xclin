import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfissionalContaEspecialidadeSaveComponent } from './profissional-conta-especialidade-save.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { RealInputModule } from 'src/app/shared/directive/real-input/real-input.module';



@NgModule({
  declarations: [
    ProfissionalContaEspecialidadeSaveComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    RealInputModule,

    MatCardModule,
    MatButtonModule,
    MatChipsModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class ProfissionalContaEspecialidadeSaveModule { }
