import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { ProfissionalRemoveDialog, ProfissionalTelaComponent } from './profissional-tela.component';
import { ClinicaAutocompleteInputModule } from 'src/app/shared/clinica-autocomplete-input/clinica-autocomplete-input.module';



@NgModule({
  declarations: [
    ProfissionalTelaComponent,
    ProfissionalRemoveDialog
  ],
  imports: [
    CommonModule,
    SharedModule,

    ClinicaAutocompleteInputModule,

    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule
  ]
})
export class ProfissionalTelaModule { }
