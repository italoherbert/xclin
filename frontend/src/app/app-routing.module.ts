import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LoginLayoutComponent } from './login-layout/login-layout.component';
import { HomeComponent } from './home/home.component';
import { UsuarioTelaComponent } from './usuario/usuario-tela/usuario-tela.component';
import { UsuarioDetalhesComponent } from './usuario/usuario-detalhes/usuario-detalhes.component';
import { UsuarioSaveComponent } from './usuario/usuario-save/usuario-save.component';
import { AppLayoutComponent } from './app-layout/app-layout.component';
import { UsuarioGrupoTelaComponent } from './usuario-grupo/usuario-grupo-tela/usuario-grupo-tela.component';
import { UsuarioGrupoDetalhesComponent } from './usuario-grupo/usuario-grupo-detalhes/usuario-grupo-detalhes.component';
import { UsuarioGrupoSaveComponent } from './usuario-grupo/usuario-grupo-save/usuario-grupo-save.component';
import { UsuarioGrupoAcessosComponent } from './usuario-grupo/usuario-grupo-acessos/usuario-grupo-acessos.component';
import { RecursoTelaComponent } from './recurso/recurso-tela/recurso-tela.component';
import { RecursoDetalhesComponent } from './recurso/recurso-detalhes/recurso-detalhes.component';
import { RecursoSaveComponent } from './recurso/recurso-save/recurso-save.component';
import { UsuarioGrupoVinculosComponent } from './usuario/usuario-grupo-vinculos/usuario-grupo-vinculos.component';
import { ClinicaTelaComponent } from './clinica/clinica-tela/clinica-tela.component';
import { ClinicaDetalhesComponent } from './clinica/clinica-detalhes/clinica-detalhes.component';
import { ClinicaSaveComponent } from './clinica/clinica-save/clinica-save.component';
import { DiretorTelaComponent } from './diretor/diretor-tela/diretor-tela.component';
import { DiretorDetalhesComponent } from './diretor/diretor-detalhes/diretor-detalhes.component';
import { DiretorSaveComponent } from './diretor/diretor-save/diretor-save.component';
import { DiretorClinicaVinculosComponent } from './diretor/diretor-clinica-vinculos/diretor-clinica-vinculos.component';
import { ProfissionalTelaComponent } from './profissional/profissional-tela/profissional-tela.component';
import { ProfissionalDetalhesComponent } from './profissional/profissional-detalhes/profissional-detalhes.component';
import { ProfissionalSaveComponent } from './profissional/profissional-save/profissional-save.component';
import { ProfissionalClinicaVinculosComponent } from './profissional/profissional-clinica-vinculos/profissional-clinica-vinculos.component';
import { RecepcionistaSaveComponent } from './recepcionista/recepcionista-save/recepcionista-save.component';
import { RecepcionistaDetalhesComponent } from './recepcionista/recepcionista-detalhes/recepcionista-detalhes.component';
import { RecepcionistaTelaComponent } from './recepcionista/recepcionista-tela/recepcionista-tela.component';
import { PacienteTelaComponent } from './paciente/paciente-tela/paciente-tela.component';
import { PacienteDetalhesComponent } from './paciente/paciente-detalhes/paciente-detalhes.component';
import { PacienteSaveComponent } from './paciente/paciente-save/paciente-save.component';
import { ConsultaDetalhesComponent } from './consulta/consulta-detalhes/consulta-detalhes.component';
import { ConsultaRemarcarComponent } from './consulta/consulta-remarcar/consulta-remarcar.component';
import { ConsultaNovaComponent } from './consulta/consulta-nova/consulta-nova.component';
import { ConsultaTelaComponent } from './consulta/consulta-tela/consulta-tela.component';
import { ConsultaFilaComponent } from './consulta/consulta-fila/consulta-fila.component';
import { NaoAdminDiretorTelaComponent } from './diretor/nao-admin-diretor-tela/nao-admin-diretor-tela.component';
import { NaoAdminProfissionalTelaComponent } from './profissional/nao-admin-profissional-tela/nao-admin-profissional-tela.component';
import { NaoAdminRecepcionistaTelaComponent } from './recepcionista/nao-admin-recepcionista-tela/nao-admin-recepcionista-tela.component';
import { NaoAdminClinicaTelaComponent } from './clinica/nao-admin-clinica-tela/nao-admin-clinica-tela.component';

const routes: Routes = [
  { path: '', redirectTo:'login', pathMatch: 'full'},
  { path: 'login', component: LoginLayoutComponent, children: [
    { path: 'lg', component: LoginComponent, outlet: 'center' },
  ] },
  { path: 'app', component: AppLayoutComponent, children: [
    { path: 'home', component: HomeComponent, outlet: 'page'},
    { path: 'usuario-tela', component: UsuarioTelaComponent, outlet: 'page' },
    { path: 'usuario-detalhes/:id', component: UsuarioDetalhesComponent, outlet: 'page' },
    { path: 'usuario-save/:id', component: UsuarioSaveComponent, outlet: 'page' },
    { path: 'usuario-grupos-vinculos/:id', component: UsuarioGrupoVinculosComponent, outlet: 'page' },

    { path: 'usuario-grupo-tela', component: UsuarioGrupoTelaComponent, outlet: 'page' },
    { path: 'usuario-grupo-detalhes/:id', component: UsuarioGrupoDetalhesComponent, outlet: 'page' },
    { path: 'usuario-grupo-save/:id', component: UsuarioGrupoSaveComponent, outlet: 'page' },
    { path: 'usuario-grupo-acessos/:id', component: UsuarioGrupoAcessosComponent, outlet: 'page' },

    { path: 'recurso-tela', component: RecursoTelaComponent, outlet: 'page' },
    { path: 'recurso-detalhes/:id', component: RecursoDetalhesComponent, outlet: 'page' },
    { path: 'recurso-save/:id', component: RecursoSaveComponent, outlet: 'page' },

    { path: 'clinica-tela', component: ClinicaTelaComponent, outlet: 'page' },
    { path: 'clinica-detalhes/:id', component: ClinicaDetalhesComponent, outlet: 'page' },
    { path: 'clinica-save/:id', component: ClinicaSaveComponent, outlet: 'page' },
    { path: 'nao-admin-clinica-tela', component: NaoAdminClinicaTelaComponent, outlet: 'page' },

    { path: 'diretor-tela', component: DiretorTelaComponent, outlet: 'page' },
    { path: 'diretor-detalhes/:id', component: DiretorDetalhesComponent, outlet: 'page' },
    { path: 'diretor-save/:id', component: DiretorSaveComponent, outlet: 'page' },
    { path: 'diretor-clinica-vinculos/:id', component: DiretorClinicaVinculosComponent, outlet: 'page' },
    { path: 'nao-admin-diretor-tela', component: NaoAdminDiretorTelaComponent, outlet: 'page' },

    { path: 'profissional-tela', component: ProfissionalTelaComponent, outlet: 'page' },
    { path: 'profissional-detalhes/:id', component: ProfissionalDetalhesComponent, outlet: 'page' },
    { path: 'profissional-save/:id', component: ProfissionalSaveComponent, outlet: 'page' },
    { path: 'profissional-clinica-vinculos/:id', component: ProfissionalClinicaVinculosComponent, outlet: 'page' },
    { path: 'nao-admin-profissional-tela', component: NaoAdminProfissionalTelaComponent, outlet: 'page' },

    { path: 'recepcionista-tela', component: RecepcionistaTelaComponent, outlet: 'page' },
    { path: 'recepcionista-detalhes/:id', component: RecepcionistaDetalhesComponent, outlet: 'page' },
    { path: 'recepcionista-save/:id', component: RecepcionistaSaveComponent, outlet: 'page' },
    { path: 'nao-admin-recepcionista-tela', component: NaoAdminRecepcionistaTelaComponent, outlet: 'page' },

    { path: 'paciente-tela', component: PacienteTelaComponent, outlet: 'page' },
    { path: 'paciente-detalhes/:id', component: PacienteDetalhesComponent, outlet: 'page' },
    { path: 'paciente-save/:id', component: PacienteSaveComponent, outlet: 'page' },
    
    { path: 'consulta-nova', component: ConsultaNovaComponent, outlet: 'page' },
    { path: 'consulta-fila', component: ConsultaFilaComponent, outlet: 'page' },
    { path: 'consulta-tela', component: ConsultaTelaComponent, outlet: 'page' },
    { path: 'consulta-remarcar/:consultaId', component: ConsultaRemarcarComponent, outlet: 'page' },
    { path: 'consulta-detalhes/:consultaId', component: ConsultaDetalhesComponent, outlet: 'page' },
  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
