import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DiretorRemoveDialog, DiretorTelaComponent } from './diretor-tela.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { ClinicaAutocompleteInputModule } from 'src/app/shared/clinica-autocomplete-input/clinica-autocomplete-input.module';
import { MatTableModule } from '@angular/material/table';
import { PaginatorModule } from 'src/app/shared/paginator/paginator.module';



@NgModule({
  declarations: [
    DiretorTelaComponent,
    DiretorRemoveDialog
  ],
  imports: [
    CommonModule,
    SharedModule,

    ClinicaAutocompleteInputModule,

    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    MatTableModule,

    PaginatorModule
  ]
})
export class DiretorTelaModule { }
