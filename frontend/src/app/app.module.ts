import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';

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
import { ConsultaModule } from './features/consulta/consulta.module';
import { ClinicaModule } from './features/clinica/clinica.module';
import { UsuarioContaModule } from './features/usuario-conta/usuario-conta.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,    

    MatNativeDateModule,

    AppLayoutModule,
    LoginLayoutModule,
    LoginModule,
    HomeModule,

    UsuarioContaModule,
    ConsultaModule,

    PacienteModule,
    DiretorModule,
    RecepcionistaModule,
    ProfissionalModule,
    EspecialidadeModule,

    ClinicaModule,
    
    UsuarioModule,
    UsuarioGrupoModule,
    RecursoModule
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue:"pt-br"}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
