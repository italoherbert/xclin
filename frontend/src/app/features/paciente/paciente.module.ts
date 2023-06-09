import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PacienteAnamneseModule } from './paciente-anamnese/paciente-anamnese.module';
import { PacienteDetalhesModule } from './paciente-detalhes/paciente-detalhes.module';
import { PacienteSaveModule } from './paciente-save/paciente-save.module';
import { PacienteTelaModule } from './paciente-tela/paciente-tela.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    PacienteTelaModule,
    PacienteDetalhesModule,
    PacienteSaveModule,
    PacienteAnamneseModule,
    /*
    SharedModule,

    FontAwesomeModule,

    BrowserAnimationsModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatOptionModule,
    MatDialogModule,
    MatSelectModule,    
    MatDatepickerModule,
    MatNativeDateModule,
    */
  ],
  exports: [
    
  ]
})
export class PacienteModule { }
