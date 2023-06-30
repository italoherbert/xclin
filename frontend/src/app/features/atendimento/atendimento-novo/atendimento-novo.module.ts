import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatStepperModule } from '@angular/material/stepper';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatAutocompleteModule } from '@angular/material/autocomplete';

import { CalendarioModule } from 'src/app/shared/calendario/calendario.module';
import { AtendimentoNovoComponent } from './atendimento-novo.component';
import { AtendimentoProfissionalSelectComponent } from './atendimento-profissional-select/atendimento-profissional-select.component';
import { AtendimentoRegistroComponent } from './atendimento-registro/atendimento-registro.component';
import { RealInputModule } from 'src/app/shared/directive/real-input/real-input.module';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { PacienteAutocompleteInputModule } from 'src/app/shared/paciente-autocomplete-input/paciente-autocomplete-input.module';



@NgModule({
  declarations: [
    AtendimentoNovoComponent,
    AtendimentoProfissionalSelectComponent,
    AtendimentoRegistroComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    CalendarioModule,
    PacienteAutocompleteInputModule,

    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    MatAutocompleteModule,
    MatCheckboxModule,
    
    RealInputModule
  ],
})
export class AtendimentoNovoModule { }
