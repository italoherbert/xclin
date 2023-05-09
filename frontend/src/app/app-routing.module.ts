import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LoginLayoutComponent } from './layout/login-layout/login-layout.component';
import { HomeComponent } from './home/home.component';
import { UsuarioTelaComponent } from './usuario/usuario-tela/usuario-tela.component';
import { UsuarioDetalhesComponent } from './usuario/usuario-detalhes/usuario-detalhes.component';
import { UsuarioSaveComponent } from './usuario/usuario-save/usuario-save.component';
import { AppLayoutComponent } from './layout/app-layout/app-layout.component';
import { UsuarioGrupoTelaComponent } from './usuario-grupo/usuario-grupo-tela/usuario-grupo-tela.component';
import { UsuarioGrupoDetalhesComponent } from './usuario-grupo/usuario-grupo-detalhes/usuario-grupo-detalhes.component';
import { UsuarioGrupoSaveComponent } from './usuario-grupo/usuario-grupo-save/usuario-grupo-save.component';
import { UsuarioGrupoAcessosComponent } from './usuario-grupo/usuario-grupo-acessos/usuario-grupo-acessos.component';
import { RecursoTelaComponent } from './recurso/recurso-tela/recurso-tela.component';
import { RecursoDetalhesComponent } from './recurso/recurso-detalhes/recurso-detalhes.component';
import { RecursoSaveComponent } from './recurso/recurso-save/recurso-save.component';
import { UsuarioGrupoVinculosSaveComponent } from './usuario/usuario-grupo-vinculos-save/usuario-grupo-vinculos-save.component';
import { ClinicaTelaComponent } from './clinica/clinica-tela/clinica-tela.component';
import { ClinicaDetalhesComponent } from './clinica/clinica-detalhes/clinica-detalhes.component';
import { ClinicaSaveComponent } from './clinica/clinica-save/clinica-save.component';
import { DiretorTelaComponent } from './diretor/diretor-tela/diretor-tela.component';
import { DiretorDetalhesComponent } from './diretor/diretor-detalhes/diretor-detalhes.component';
import { DiretorSaveComponent } from './diretor/diretor-save/diretor-save.component';

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
    { path: 'usuario-grupos-vinculos-save/:id', component: UsuarioGrupoVinculosSaveComponent, outlet: 'page' },

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

    { path: 'diretor-tela', component: DiretorTelaComponent, outlet: 'page' },
    { path: 'diretor-detalhes/:id', component: DiretorDetalhesComponent, outlet: 'page' },
    { path: 'diretor-save/:id', component: DiretorSaveComponent, outlet: 'page' },

  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
