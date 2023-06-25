import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RelatorioProntuarioComponent } from './relatorio-prontuario.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { PacienteAutocompleteInputModule } from 'src/app/shared/paciente-autocomplete-input/paciente-autocomplete-input.module';



@NgModule({
  declarations: [
    RelatorioProntuarioComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    PacienteAutocompleteInputModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule
  ]
})
export class RelatorioProntuarioModule { }
