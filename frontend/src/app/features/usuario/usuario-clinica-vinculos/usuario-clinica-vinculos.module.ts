import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioClinicaVinculosComponent } from './usuario-clinica-vinculos.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { ClinicaAutocompleteInputModule } from 'src/app/shared/clinica-autocomplete-input/clinica-autocomplete-input.module';
import { MatTableModule } from '@angular/material/table';
import { PaginatorModule } from 'src/app/shared/paginator/paginator.module';



@NgModule({
  declarations: [
    UsuarioClinicaVinculosComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    ClinicaAutocompleteInputModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatAutocompleteModule,
    MatTableModule,

    PaginatorModule
  ]
})
export class UsuarioClinicaVinculosModule { }
