import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClinicaDetalhesModule } from './clinica-detalhes/clinica-detalhes.module';
import { ClinicaSaveModule } from './clinica-save/clinica-save.module';
import { ClinicaTelaModule } from './clinica-tela/clinica-tela.module';
import { NaoAdminClinicaTelaModule } from './nao-admin-clinica-tela/nao-admin-clinica-tela.module';
import { ClinicaLogoModule } from './clinica-logo/clinica-logo.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    ClinicaDetalhesModule,
    ClinicaSaveModule,
    ClinicaTelaModule,
    NaoAdminClinicaTelaModule,

    ClinicaLogoModule
  ]
})
export class ClinicaModule { }
