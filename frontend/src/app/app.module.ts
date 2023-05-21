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
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatStepperModule } from '@angular/material/stepper';

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
import { ConsultaDetalhesComponent } from './consulta/consulta-detalhes/consulta-detalhes.component';
import { ConsultaRegistroComponent } from './consulta/consulta-nova/consulta-registro/consulta-registro.component';
import { CalendarioComponent } from './calendario/calendario.component';
import { ConsultaRemarcarComponent } from './consulta/consulta-remarcar/consulta-remarcar.component';
import { ConsultaNovaComponent } from './consulta/consulta-nova/consulta-nova.component';
import { ConsultaProfissionalSelectComponent } from './consulta/consulta-nova/consulta-profissional-select/consulta-profissional-select.component';
import { ConsultaRemoveDialog, ConsultaTelaComponent } from './consulta/consulta-tela/consulta-tela.component';
import { ConsultaFilaComponent } from './consulta/consulta-fila/consulta-fila.component';
import { NaoAdminDiretorTelaComponent } from './diretor/nao-admin-diretor-tela/nao-admin-diretor-tela.component';
import { NaoAdminProfissionalTelaComponent } from './profissional/nao-admin-profissional-tela/nao-admin-profissional-tela.component';
import { NaoAdminRecepcionistaTelaComponent } from './recepcionista/nao-admin-recepcionista-tela/nao-admin-recepcionista-tela.component';
import { NaoAdminClinicaTelaComponent } from './clinica/nao-admin-clinica-tela/nao-admin-clinica-tela.component';
import { ConsultaAlterarComponent } from './consulta/consulta-alterar/consulta-alterar.component';
import { RecepcionistaContaAlterarComponent } from './usuario-conta/recepcionista/recepcionista-conta-alterar/recepcionista-conta-alterar.component';
import { ProfissionalContaAlterarComponent } from './usuario-conta/profissional/profissional-conta-alterar/profissional-conta-alterar.component';
import { DiretorContaAlterarComponent } from './usuario-conta/diretor/diretor-conta-alterar/diretor-conta-alterar.component';
import { UsuarioContaAlteraSenhaComponent } from './usuario-conta/usuario-conta-altera-senha/usuario-conta-altera-senha.component';
import { ProfissionalContaDetalhesComponent } from './usuario-conta/profissional/profissional-conta-detalhes/profissional-conta-detalhes.component';
import { RecepcionistaContaDetalhesComponent } from './usuario-conta/recepcionista/recepcionista-conta-detalhes/recepcionista-conta-detalhes.component';
import { DiretorContaDetalhesComponent } from './usuario-conta/diretor/diretor-conta-detalhes/diretor-conta-detalhes.component';
import { EspecialidadeRemoveDialog, EspecialidadeTelaComponent } from './especialidade/especialidade-tela/especialidade-tela.component';
import { EspecialidadeSaveComponent } from './especialidade/especialidade-save/especialidade-save.component';
import { EspecialidadeDetalhesComponent } from './especialidade/especialidade-detalhes/especialidade-detalhes.component';


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
    PacienteRemoveDialog,
    ConsultaDetalhesComponent,
    ConsultaRegistroComponent,
    CalendarioComponent,
    ConsultaRemarcarComponent,
    ConsultaNovaComponent,
    ConsultaProfissionalSelectComponent,
    ConsultaTelaComponent,
    ConsultaRemoveDialog,
    ConsultaFilaComponent,
    NaoAdminDiretorTelaComponent,
    NaoAdminProfissionalTelaComponent,
    NaoAdminRecepcionistaTelaComponent,
    NaoAdminClinicaTelaComponent,
    ConsultaAlterarComponent,
    RecepcionistaContaAlterarComponent,
    ProfissionalContaAlterarComponent,
    DiretorContaAlterarComponent,
    UsuarioContaAlteraSenhaComponent,
    ProfissionalContaDetalhesComponent,
    RecepcionistaContaDetalhesComponent,
    DiretorContaDetalhesComponent,
    EspecialidadeTelaComponent,
    EspecialidadeSaveComponent,
    EspecialidadeDetalhesComponent,
    EspecialidadeRemoveDialog
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
    MatDividerModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatAutocompleteModule,
    MatStepperModule
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue:"pt-br"}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
