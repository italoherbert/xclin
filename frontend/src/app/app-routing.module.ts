import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UsuarioFiltroComponent } from './usuario/usuario-filtro/usuario-filtro.component';
import { UsuarioDetalhesComponent } from './usuario/usuario-detalhes/usuario-detalhes.component';
import { UsuarioSaveComponent } from './usuario/usuario-save/usuario-save.component';
import { HomeComponent } from './home/home.component';
import { LoginLayoutComponent } from './layout/login-layout/login-layout.component';
import { SistemaLayoutComponent } from './layout/sistema-layout/sistema-layout.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginLayoutComponent, children: [
    { path: '', redirectTo: '(center:lg)', pathMatch: 'full'},
    { path: 'lg', component: LoginComponent, outlet: 'center' },
  ] },
  { path: 'scm', component: SistemaLayoutComponent, children: [
    { path: '', redirectTo: 'home', pathMatch: 'full'},
    { path: 'home', component: HomeComponent },
    { path: 'usuario-filtro', component: UsuarioFiltroComponent },
    { path: 'usuario-detalhes', component: UsuarioDetalhesComponent },
    { path: 'usuario-save', component: UsuarioSaveComponent }
  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
