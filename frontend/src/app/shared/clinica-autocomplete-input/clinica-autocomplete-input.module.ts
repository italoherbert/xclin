import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClinicaAutocompleteInputComponent } from './clinica-autocomplete-input.component';
import { SharedModule } from '../shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatAutocompleteModule } from '@angular/material/autocomplete';



@NgModule({
  declarations: [
    ClinicaAutocompleteInputComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule
  ],
  exports: [
    ClinicaAutocompleteInputComponent
  ]
})
export class ClinicaAutocompleteInputModule { }
