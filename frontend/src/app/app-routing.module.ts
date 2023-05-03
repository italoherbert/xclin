import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LoginLayoutComponent } from './layout/login-layout/login-layout.component';
import { SistemaLayoutComponent } from './layout/sistema-layout/sistema-layout.component';
import { HomeComponent } from './home/home.component';
import { UsuarioTelaComponent } from './usuario/usuario-tela/usuario-tela.component';
import { UsuarioDetalhesComponent } from './usuario/usuario-detalhes/usuario-detalhes.component';
import { UsuarioSaveComponent } from './usuario/usuario-save/usuario-save.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginLayoutComponent, children: [
    { path: 'lg', component: LoginComponent, outlet: 'center' },
  ] },
  { path: 'scm', component: SistemaLayoutComponent, children: [
    { path: 'home', component: HomeComponent, outlet: 'page'},
    { path: 'usuario-tela', component: UsuarioTelaComponent, outlet: 'page' },
    { path: 'usuario-detalhes', component: UsuarioDetalhesComponent, outlet: 'page' },
    { path: 'usuario-save', component: UsuarioSaveComponent, outlet: 'page' }
  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
