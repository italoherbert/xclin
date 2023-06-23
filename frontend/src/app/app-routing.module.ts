import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { LoginLayoutComponent } from './features/login-layout/login-layout.component';
import { HomeComponent } from './features/home/home.component';
import { UsuarioTelaComponent } from './features/usuario/usuario-tela/usuario-tela.component';
import { UsuarioDetalhesComponent } from './features/usuario/usuario-detalhes/usuario-detalhes.component';
import { UsuarioSaveComponent } from './features/usuario/usuario-save/usuario-save.component';
import { AppLayoutComponent } from './features/app-layout/app-layout.component';
import { UsuarioGrupoTelaComponent } from './features/usuario-grupo/usuario-grupo-tela/usuario-grupo-tela.component';
import { UsuarioGrupoDetalhesComponent } from './features/usuario-grupo/usuario-grupo-detalhes/usuario-grupo-detalhes.component';
import { UsuarioGrupoSaveComponent } from './features/usuario-grupo/usuario-grupo-save/usuario-grupo-save.component';
import { UsuarioGrupoAcessosComponent } from './features/usuario-grupo/usuario-grupo-acessos/usuario-grupo-acessos.component';
import { RecursoTelaComponent } from './features/recurso/recurso-tela/recurso-tela.component';
import { RecursoDetalhesComponent } from './features/recurso/recurso-detalhes/recurso-detalhes.component';
import { RecursoSaveComponent } from './features/recurso/recurso-save/recurso-save.component';
import { UsuarioGrupoVinculosComponent } from './features/usuario/usuario-grupo-vinculos/usuario-grupo-vinculos.component';
import { ClinicaTelaComponent } from './features/clinica/clinica-tela/clinica-tela.component';
import { ClinicaDetalhesComponent } from './features/clinica/clinica-detalhes/clinica-detalhes.component';
import { ClinicaSaveComponent } from './features/clinica/clinica-save/clinica-save.component';
import { DiretorTelaComponent } from './features/diretor/diretor-tela/diretor-tela.component';
import { DiretorDetalhesComponent } from './features/diretor/diretor-detalhes/diretor-detalhes.component';
import { DiretorSaveComponent } from './features/diretor/diretor-save/diretor-save.component';
import { ProfissionalTelaComponent } from './features/profissional/profissional-tela/profissional-tela.component';
import { ProfissionalDetalhesComponent } from './features/profissional/profissional-detalhes/profissional-detalhes.component';
import { ProfissionalSaveComponent } from './features/profissional/profissional-save/profissional-save.component';
import { RecepcionistaSaveComponent } from './features/recepcionista/recepcionista-save/recepcionista-save.component';
import { RecepcionistaDetalhesComponent } from './features/recepcionista/recepcionista-detalhes/recepcionista-detalhes.component';
import { RecepcionistaTelaComponent } from './features/recepcionista/recepcionista-tela/recepcionista-tela.component';
import { PacienteTelaComponent } from './features/paciente/paciente-tela/paciente-tela.component';
import { PacienteDetalhesComponent } from './features/paciente/paciente-detalhes/paciente-detalhes.component';
import { PacienteSaveComponent } from './features/paciente/paciente-save/paciente-save.component';
import { ConsultaDetalhesComponent } from './features/consulta/consulta-detalhes/consulta-detalhes.component';
import { ConsultaRemarcarComponent } from './features/consulta/consulta-remarcar/consulta-remarcar.component';
import { ConsultaNovaComponent } from './features/consulta/consulta-nova/consulta-nova.component';
import { ConsultaTelaComponent } from './features/consulta/consulta-tela/consulta-tela.component';
import { ConsultaFilaComponent } from './features/consulta/consulta-fila/consulta-fila.component';
import { NaoAdminDiretorTelaComponent } from './features/diretor/nao-admin-diretor-tela/nao-admin-diretor-tela.component';
import { NaoAdminProfissionalTelaComponent } from './features/profissional/nao-admin-profissional-tela/nao-admin-profissional-tela.component';
import { NaoAdminRecepcionistaTelaComponent } from './features/recepcionista/nao-admin-recepcionista-tela/nao-admin-recepcionista-tela.component';
import { NaoAdminClinicaTelaComponent } from './features/clinica/nao-admin-clinica-tela/nao-admin-clinica-tela.component';
import { ConsultaAlterarComponent } from './features/consulta/consulta-alterar/consulta-alterar.component';
import { ProfissionalContaAlterarComponent } from './features/usuario-conta/profissional-conta/profissional-conta-alterar/profissional-conta-alterar.component';
import { RecepcionistaContaAlterarComponent } from './features/usuario-conta/recepcionista-conta/recepcionista-conta-alterar/recepcionista-conta-alterar.component';
import { DiretorContaAlterarComponent } from './features/usuario-conta/diretor-conta/diretor-conta-alterar/diretor-conta-alterar.component';
import { ContaAlterarSenhaComponent } from './features/usuario-conta/conta-alterar-senha/conta-alterar-senha.component';
import { ProfissionalContaDetalhesComponent } from './features/usuario-conta/profissional-conta/profissional-conta-detalhes/profissional-conta-detalhes.component';
import { RecepcionistaContaDetalhesComponent } from './features/usuario-conta/recepcionista-conta/recepcionista-conta-detalhes/recepcionista-conta-detalhes.component';
import { DiretorContaDetalhesComponent } from './features/usuario-conta/diretor-conta/diretor-conta-detalhes/diretor-conta-detalhes.component';
import { EspecialidadeTelaComponent } from './features/especialidade/especialidade-tela/especialidade-tela.component';
import { EspecialidadeDetalhesComponent } from './features/especialidade/especialidade-detalhes/especialidade-detalhes.component';
import { EspecialidadeSaveComponent } from './features/especialidade/especialidade-save/especialidade-save.component';
import { ProfissionalContaEspecialidadeSaveComponent } from './features/usuario-conta/profissional-conta/profissional-conta-especialidade-save/profissional-conta-especialidade-save.component';
import { ProfissionalContaEspecialidadesComponent } from './features/usuario-conta/profissional-conta/profissional-conta-especialidades/profissional-conta-especialidades.component';
import { ConsultaAtendimentoComponent } from './features/consulta/consulta-atendimento/consulta-atendimento.component';
import { PacienteAnamneseComponent } from './features/paciente/paciente-anamnese/paciente-anamnese.component';
import { ConsultaAgendaComponent } from './features/consulta/consulta-agenda/consulta-agenda.component';
import { ConsultaFilaCompletaComponent } from './features/consulta/consulta-fila-completa/consulta-fila-completa.component';
import { LancamentoTelaComponent } from './features/lancamento/lancamento-tela/lancamento-tela.component';
import { LancamentoNovoComponent } from './features/lancamento/lancamento-novo/lancamento-novo.component';
import { LancamentoDetalhesComponent } from './features/lancamento/lancamento-detalhes/lancamento-detalhes.component';
import { UsuarioClinicaVinculosComponent } from './features/usuario/usuario-clinica-vinculos/usuario-clinica-vinculos.component';
import { RelatorioBalancoDiaComponent } from './features/relatorio/relatorio-balanco-dia/relatorio-balanco-dia.component';
import { AnamneseModeloTelaComponent } from './features/anamnese-modelo/anamnese-modelo-tela/anamnese-modelo-tela.component';
import { AnamneseModeloDetalhesComponent } from './features/anamnese-modelo/anamnese-modelo-detalhes/anamnese-modelo-detalhes.component';
import { AnamneseModeloSaveComponent } from './features/anamnese-modelo/anamnese-modelo-save/anamnese-modelo-save.component';
import { AnamneseModeloPerguntaSaveComponent } from './features/anamnese-modelo/anamnese-modelo-pergunta-save/anamnese-modelo-pergunta-save.component';

const routes: Routes = [
  { path: '', redirectTo:'login', pathMatch: 'full'},
  { path: 'login', component: LoginLayoutComponent, children: [
    { path: 'lg', component: LoginComponent, outlet: 'center' },
  ] },
  { path: 'app', component: AppLayoutComponent, children: [
    { path: 'home', component: HomeComponent, outlet: 'page'},

    { path: 'usuario-conta-alterar-senha', component: ContaAlterarSenhaComponent, outlet: 'page' },
    
    { path: 'profissional-conta-especialidades', component: ProfissionalContaEspecialidadesComponent, outlet: 'page' },
    { path: 'profissional-conta-especialidade-save', component: ProfissionalContaEspecialidadeSaveComponent, outlet: 'page' },

    { path: 'profissional-conta-detalhes', component: ProfissionalContaDetalhesComponent, outlet: 'page' },
    { path: 'recepcionista-conta-detalhes', component: RecepcionistaContaDetalhesComponent, outlet: 'page' },
    { path: 'diretor-conta-detalhes', component: DiretorContaDetalhesComponent, outlet: 'page' },
    
    { path: 'profissional-conta-alterar', component: ProfissionalContaAlterarComponent, outlet: 'page'},
    { path: 'recepcionista-conta-alterar', component: RecepcionistaContaAlterarComponent, outlet: 'page'},
    { path: 'diretor-conta-alterar', component: DiretorContaAlterarComponent, outlet: 'page'},

    { path: 'usuario-tela', component: UsuarioTelaComponent, outlet: 'page' },
    { path: 'usuario-detalhes/:id', component: UsuarioDetalhesComponent, outlet: 'page' },
    { path: 'usuario-save/:id', component: UsuarioSaveComponent, outlet: 'page' },
    { path: 'usuario-grupos-vinculos/:id', component: UsuarioGrupoVinculosComponent, outlet: 'page' },
    { path: 'usuario-clinica-vinculos/:id', component: UsuarioClinicaVinculosComponent, outlet: 'page' },    

    { path: 'usuario-grupo-tela', component: UsuarioGrupoTelaComponent, outlet: 'page' },
    { path: 'usuario-grupo-detalhes/:id', component: UsuarioGrupoDetalhesComponent, outlet: 'page' },
    { path: 'usuario-grupo-save/:id', component: UsuarioGrupoSaveComponent, outlet: 'page' },
    { path: 'usuario-grupo-acessos/:id', component: UsuarioGrupoAcessosComponent, outlet: 'page' },

    { path: 'recurso-tela', component: RecursoTelaComponent, outlet: 'page' },
    { path: 'recurso-detalhes/:id', component: RecursoDetalhesComponent, outlet: 'page' },
    { path: 'recurso-save/:id', component: RecursoSaveComponent, outlet: 'page' },

    { path: 'especialidade-tela', component: EspecialidadeTelaComponent, outlet: 'page' },
    { path: 'especialidade-detalhes/:id', component: EspecialidadeDetalhesComponent, outlet: 'page' },
    { path: 'especialidade-save/:id', component: EspecialidadeSaveComponent, outlet: 'page' },

    { path: 'clinica-tela', component: ClinicaTelaComponent, outlet: 'page' },
    { path: 'clinica-detalhes/:id', component: ClinicaDetalhesComponent, outlet: 'page' },
    { path: 'clinica-save/:id', component: ClinicaSaveComponent, outlet: 'page' },
    { path: 'nao-admin-clinica-tela', component: NaoAdminClinicaTelaComponent, outlet: 'page' },

    { path: 'diretor-tela', component: DiretorTelaComponent, outlet: 'page' },
    { path: 'diretor-detalhes/:id', component: DiretorDetalhesComponent, outlet: 'page' },
    { path: 'diretor-save/:id', component: DiretorSaveComponent, outlet: 'page' },
    { path: 'nao-admin-diretor-tela', component: NaoAdminDiretorTelaComponent, outlet: 'page' },

    { path: 'profissional-tela', component: ProfissionalTelaComponent, outlet: 'page' },
    { path: 'profissional-detalhes/:id', component: ProfissionalDetalhesComponent, outlet: 'page' },
    { path: 'profissional-save/:id', component: ProfissionalSaveComponent, outlet: 'page' },
    { path: 'nao-admin-profissional-tela', component: NaoAdminProfissionalTelaComponent, outlet: 'page' },

    { path: 'recepcionista-tela', component: RecepcionistaTelaComponent, outlet: 'page' },
    { path: 'recepcionista-detalhes/:id', component: RecepcionistaDetalhesComponent, outlet: 'page' },
    { path: 'recepcionista-save/:id', component: RecepcionistaSaveComponent, outlet: 'page' },
    { path: 'nao-admin-recepcionista-tela', component: NaoAdminRecepcionistaTelaComponent, outlet: 'page' },

    { path: 'paciente-tela', component: PacienteTelaComponent, outlet: 'page' },
    { path: 'paciente-detalhes/:id', component: PacienteDetalhesComponent, outlet: 'page' },
    { path: 'paciente-save/:id', component: PacienteSaveComponent, outlet: 'page' },
    { path: 'paciente-anamnese/:id', component: PacienteAnamneseComponent, outlet: 'page' },
    
    { path: 'consulta-nova', component: ConsultaNovaComponent, outlet: 'page' },
    { path: 'consulta-tela', component: ConsultaTelaComponent, outlet: 'page' },
    { path: 'consulta-fila', component: ConsultaFilaComponent, outlet: 'page' },
    { path: 'consulta-fila-completa/:clinicaId/:profissionalId/:ano/:mes/:dia/:turno', component: ConsultaFilaCompletaComponent, outlet: 'page' },
    { path: 'consulta-atendimento', component: ConsultaAtendimentoComponent, outlet: 'page' },
    { path: 'consulta-remarcar/:consultaId', component: ConsultaRemarcarComponent, outlet: 'page' },
    { path: 'consulta-alterar/:consultaId', component: ConsultaAlterarComponent, outlet: 'page' },
    { path: 'consulta-detalhes/:consultaId', component: ConsultaDetalhesComponent, outlet: 'page' },
    { path: 'consulta-agenda', component: ConsultaAgendaComponent, outlet: 'page' },

    { path: 'lancamento-tela', component: LancamentoTelaComponent, outlet: 'page' },
    { path: 'lancamento-novo', component: LancamentoNovoComponent, outlet: 'page' },
    { path: 'lancamento-detalhes/:lancamentoId', component: LancamentoDetalhesComponent, outlet: 'page' },

    { path: 'anamnese-modelo-tela', component: AnamneseModeloTelaComponent, outlet: 'page' },
    { path: 'anamnese-modelo-detalhes/:id', component: AnamneseModeloDetalhesComponent, outlet: 'page' },
    { path: 'anamnese-modelo-save/:id', component: AnamneseModeloSaveComponent, outlet: 'page' },
    { path: 'anamnese-modelo-pergunta-save/:modeloId/:perguntaId', component: AnamneseModeloPerguntaSaveComponent, outlet: 'page' },

    { path: 'relatorio-balanco-dia', component: RelatorioBalancoDiaComponent, outlet: 'page' }
  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
