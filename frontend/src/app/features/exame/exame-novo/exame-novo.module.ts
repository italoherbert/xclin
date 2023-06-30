import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExameNovoComponent } from './exame-novo.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatStepperModule } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { PacienteAutocompleteInputModule } from 'src/app/shared/paciente-autocomplete-input/paciente-autocomplete-input.module';



@NgModule({
  declarations: [
    ExameNovoComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    PacienteAutocompleteInputModule,

    MatCardModule,
    MatStepperModule,
    MatButtonModule
  ]
})
export class ExameNovoModule { }
