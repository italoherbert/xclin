import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule} from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { MatChipsModule } from '@angular/material/chips';
import { MatDividerModule } from '@angular/material/divider';

import { AppComponent } from './app.component';
import { LoginLayoutComponent } from './login-layout/login-layout.component';
import { AppLayoutComponent } from './app-layout/app-layout.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

import { UsuarioRemoveDialog, UsuarioTelaComponent } from './usuario/usuario-tela/usuario-tela.component';
import { UsuarioDetalhesComponent } from './usuario/usuario-detalhes/usuario-detalhes.component';
import { UsuarioSaveComponent } from './usuario/usuario-save/usuario-save.component';
import { UsuarioGrupoRemoveDialog, UsuarioGrupoTelaComponent } from './usuario-grupo/usuario-grupo-tela/usuario-grupo-tela.component';
import { UsuarioGrupoDetalhesComponent } from './usuario-grupo/usuario-grupo-detalhes/usuario-grupo-detalhes.component';
import { UsuarioGrupoSaveComponent } from './usuario-grupo/usuario-grupo-save/usuario-grupo-save.component';
import { UsuarioGrupoAcessosComponent } from './usuario-grupo/usuario-grupo-acessos/usuario-grupo-acessos.component';
import { RecursoRemoveDialog, RecursoTelaComponent } from './recurso/recurso-tela/recurso-tela.component';
import { RecursoDetalhesComponent } from './recurso/recurso-detalhes/recurso-detalhes.component';
import { RecursoSaveComponent } from './recurso/recurso-save/recurso-save.component';
import { UsuarioGrupoVinculosComponent } from './usuario/usuario-grupo-vinculos/usuario-grupo-vinculos.component';
import { ClinicaRemoveDialog, ClinicaTelaComponent } from './clinica/clinica-tela/clinica-tela.component';
import { ClinicaSaveComponent } from './clinica/clinica-save/clinica-save.component';
import { ClinicaDetalhesComponent } from './clinica/clinica-detalhes/clinica-detalhes.component';
import { DiretorRemoveDialog, DiretorTelaComponent } from './diretor/diretor-tela/diretor-tela.component';
import { DiretorDetalhesComponent } from './diretor/diretor-detalhes/diretor-detalhes.component';
import { DiretorSaveComponent } from './diretor/diretor-save/diretor-save.component';
import { DiretorClinicaVinculosComponent } from './diretor/diretor-clinica-vinculos/diretor-clinica-vinculos.component';
import { ProfissionalRemoveDialog, ProfissionalTelaComponent } from './profissional/profissional-tela/profissional-tela.component';
import { ProfissionalDetalhesComponent } from './profissional/profissional-detalhes/profissional-detalhes.component';
import { ProfissionalSaveComponent } from './profissional/profissional-save/profissional-save.component';
import { ProfissionalClinicaVinculosComponent } from './profissional/profissional-clinica-vinculos/profissional-clinica-vinculos.component';
import { RecepcionistaRemoveDialog, RecepcionistaTelaComponent } from './recepcionista/recepcionista-tela/recepcionista-tela.component';
import { RecepcionistaDetalhesComponent } from './recepcionista/recepcionista-detalhes/recepcionista-detalhes.component';
import { RecepcionistaSaveComponent } from './recepcionista/recepcionista-save/recepcionista-save.component';
import { PacienteRemoveDialog, PacienteTelaComponent } from './paciente/paciente-tela/paciente-tela.component';
import { PacienteDetalhesComponent } from './paciente/paciente-detalhes/paciente-detalhes.component';
import { PacienteSaveComponent } from './paciente/paciente-save/paciente-save.component';

@NgModule({
  declarations: [
    AppComponent,

    LoginLayoutComponent,
    AppLayoutComponent,

    LoginComponent,
    HomeComponent,
    
    UsuarioDetalhesComponent,
    UsuarioSaveComponent,
    UsuarioTelaComponent,
    UsuarioGrupoTelaComponent,
    UsuarioGrupoDetalhesComponent,
    UsuarioGrupoSaveComponent,
    UsuarioGrupoAcessosComponent,
    RecursoTelaComponent,
    RecursoDetalhesComponent,
    RecursoSaveComponent,

    UsuarioGrupoRemoveDialog,
    UsuarioRemoveDialog,
    RecursoRemoveDialog,
    UsuarioGrupoVinculosComponent,
    ClinicaTelaComponent,
    ClinicaSaveComponent,
    ClinicaDetalhesComponent,
    ClinicaRemoveDialog,
    DiretorTelaComponent,
    DiretorDetalhesComponent,
    DiretorSaveComponent,
    DiretorRemoveDialog,
    DiretorClinicaVinculosComponent,
    ProfissionalTelaComponent,
    ProfissionalDetalhesComponent,
    ProfissionalSaveComponent,
    ProfissionalClinicaVinculosComponent,
    ProfissionalRemoveDialog,
    RecepcionistaTelaComponent,
    RecepcionistaDetalhesComponent,
    RecepcionistaSaveComponent,
    RecepcionistaRemoveDialog,
    PacienteTelaComponent,
    PacienteDetalhesComponent,
    PacienteSaveComponent,
    PacienteRemoveDialog
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    FontAwesomeModule,

    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatMenuModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDialogModule,
    MatChipsModule,
    MatDividerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
