import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PacienteAutocompleteInputComponent } from './paciente-autocomplete-input.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { SharedModule } from '../shared.module';


@NgModule({
  declarations: [
    PacienteAutocompleteInputComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule
  ],
  exports: [
    PacienteAutocompleteInputComponent
  ]
})
export class PacienteAutocompleteInputModule { }
