import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClinicaExameSaveModule } from './clinica-exame-save/clinica-exame-save.module';
import { ClinicaExameDetalhesModule } from './clinica-exame-detalhes/clinica-exame-detalhes.module';
import { ClinicaExameTelaModule } from './clinica-exame-tela/clinica-exame-tela.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    ClinicaExameTelaModule,
    ClinicaExameDetalhesModule,
    ClinicaExameSaveModule
  ]
})
export class ClinicaExameModule { }
