import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PacienteAnamneseModule } from './paciente-anamnese/paciente-anamnese.module';
import { PacienteDetalhesModule } from './paciente-detalhes/paciente-detalhes.module';
import { PacienteSaveModule } from './paciente-save/paciente-save.module';
import { PacienteTelaModule } from './paciente-tela/paciente-tela.module';
import { PacienteAnexosModule } from './paciente-anexos/paciente-anexos.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    PacienteTelaModule,
    PacienteDetalhesModule,
    PacienteSaveModule,
    PacienteAnamneseModule,    
    PacienteAnexosModule
  ],
  exports: [
    
  ]
})
export class PacienteModule { }
