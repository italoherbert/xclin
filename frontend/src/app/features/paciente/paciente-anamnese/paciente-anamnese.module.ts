import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PacienteAnamneseComponent, PacienteAnamneseVinculaModeloDialog } from './paciente-anamnese.component';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    PacienteAnamneseComponent,
    PacienteAnamneseVinculaModeloDialog
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatRadioModule,
    MatSelectModule,
    MatOptionModule,
    MatRadioModule,
    MatDialogModule
  ]
})
export class PacienteAnamneseModule { }
