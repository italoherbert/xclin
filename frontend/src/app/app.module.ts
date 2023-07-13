import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

//import { MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';

import { AppComponent } from './app.component';
import { AppLayoutModule } from './features/app-layout/app-layout.module';
import { LoginLayoutModule } from './features/login-layout/login-layout.module';
import { UsuarioModule } from './features/usuario/usuario.module';
import { PacienteModule } from './features/paciente/paciente.module';
import { UsuarioGrupoModule } from './features/usuario-grupo/usuario-grupo.module';
import { RecursoModule } from './features/recurso/recurso.module';
import { RecepcionistaModule } from './features/recepcionista/recepcionista.module';
import { ProfissionalModule } from './features/profissional/profissional.module';
import { LoginModule } from './features/login/login.module';
import { HomeModule } from './features/home/home.module';
import { EspecialidadeModule } from './features/especialidade/especialidade.module';
import { DiretorModule } from './features/diretor/diretor.module';
import { AtendimentoModule } from './features/atendimento/atendimento.module';
import { ClinicaModule } from './features/clinica/clinica.module';
import { UsuarioContaModule } from './features/conta/usuario-conta.module';
import { LancamentoModule } from './features/lancamento/lancamento.module';
import { RelatorioModule } from './features/relatorio/relatorio.module';
import { AnamneseModeloModule } from './features/anamnese-modelo/anamnese-modelo.module';
import { ExameModule } from './features/exame/exame.module';

import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';

export const MY_DATE_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@NgModule({
  declarations: [
    AppComponent,    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,    

    AppLayoutModule,
    LoginLayoutModule,
    LoginModule,
    HomeModule,

    UsuarioContaModule,
    AtendimentoModule,

    PacienteModule,
    DiretorModule,
    RecepcionistaModule,
    ProfissionalModule,
    EspecialidadeModule,

    ClinicaModule,
    
    UsuarioModule,
    UsuarioGrupoModule,
    RecursoModule,

    AnamneseModeloModule,
    ExameModule, 
    LancamentoModule,

    RelatorioModule
  ],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },

    {provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
