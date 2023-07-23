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
import { AtendimentoDetalhesComponent } from './features/atendimento/atendimento-detalhes/atendimento-detalhes.component';
import { AtendimentoRemarcarComponent } from './features/atendimento/atendimento-remarcar/atendimento-remarcar.component';
import { AtendimentoNovoComponent } from './features/atendimento/atendimento-novo/atendimento-novo.component';
import { AtendimentoTelaComponent } from './features/atendimento/atendimento-tela/atendimento-tela.component';
import { AtendimentoFilaComponent } from './features/atendimento/atendimento-fila/atendimento-fila.component';
import { NaoAdminDiretorTelaComponent } from './features/diretor/nao-admin-diretor-tela/nao-admin-diretor-tela.component';
import { NaoAdminProfissionalTelaComponent } from './features/profissional/nao-admin-profissional-tela/nao-admin-profissional-tela.component';
import { NaoAdminRecepcionistaTelaComponent } from './features/recepcionista/nao-admin-recepcionista-tela/nao-admin-recepcionista-tela.component';
import { NaoAdminClinicaTelaComponent } from './features/clinica/nao-admin-clinica-tela/nao-admin-clinica-tela.component';
import { AtendimentoAlterarComponent } from './features/atendimento/atendimento-alterar/atendimento-alterar.component';
import { ProfissionalContaAlterarComponent } from './features/conta/profissional-conta/profissional-conta-alterar/profissional-conta-alterar.component';
import { RecepcionistaContaAlterarComponent } from './features/conta/recepcionista-conta/recepcionista-conta-alterar/recepcionista-conta-alterar.component';
import { DiretorContaAlterarComponent } from './features/conta/diretor-conta/diretor-conta-alterar/diretor-conta-alterar.component';
import { ContaAlterarSenhaComponent } from './features/conta/conta-alterar-senha/conta-alterar-senha.component';
import { ProfissionalContaDetalhesComponent } from './features/conta/profissional-conta/profissional-conta-detalhes/profissional-conta-detalhes.component';
import { RecepcionistaContaDetalhesComponent } from './features/conta/recepcionista-conta/recepcionista-conta-detalhes/recepcionista-conta-detalhes.component';
import { DiretorContaDetalhesComponent } from './features/conta/diretor-conta/diretor-conta-detalhes/diretor-conta-detalhes.component';
import { EspecialidadeTelaComponent } from './features/especialidade/especialidade-tela/especialidade-tela.component';
import { EspecialidadeDetalhesComponent } from './features/especialidade/especialidade-detalhes/especialidade-detalhes.component';
import { EspecialidadeSaveComponent } from './features/especialidade/especialidade-save/especialidade-save.component';
import { ProfissionalContaEspecialidadeSaveComponent } from './features/conta/profissional-conta/profissional-conta-especialidade-save/profissional-conta-especialidade-save.component';
import { ProfissionalContaEspecialidadesComponent } from './features/conta/profissional-conta/profissional-conta-especialidades/profissional-conta-especialidades.component';
import { AtendimentoIniciadoComponent } from './features/atendimento/atendimento-iniciado/atendimento-iniciado.component';
import { PacienteAnamneseComponent } from './features/paciente/paciente-anamnese/paciente-anamnese.component';
import { AtendimentoAgendaComponent } from './features/atendimento/atendimento-agenda/atendimento-agenda.component';
import { AtendimentoFilaCompletaComponent } from './features/atendimento/atendimento-fila-completa/atendimento-fila-completa.component';
import { LancamentoTelaComponent } from './features/lancamento/lancamento-tela/lancamento-tela.component';
import { LancamentoNovoComponent } from './features/lancamento/lancamento-novo/lancamento-novo.component';
import { LancamentoDetalhesComponent } from './features/lancamento/lancamento-detalhes/lancamento-detalhes.component';
import { UsuarioClinicaVinculosComponent } from './features/usuario/usuario-clinica-vinculos/usuario-clinica-vinculos.component';
import { RelatorioBalancoDiaComponent } from './features/relatorio/relatorio-balanco-dia/relatorio-balanco-dia.component';
import { AnamneseModeloTelaComponent } from './features/anamnese-modelo/anamnese-modelo-tela/anamnese-modelo-tela.component';
import { AnamneseModeloDetalhesComponent } from './features/anamnese-modelo/anamnese-modelo-detalhes/anamnese-modelo-detalhes.component';
import { AnamneseModeloSaveComponent } from './features/anamnese-modelo/anamnese-modelo-save/anamnese-modelo-save.component';
import { AnamneseModeloPerguntaSaveComponent } from './features/anamnese-modelo/anamnese-modelo-pergunta-save/anamnese-modelo-pergunta-save.component';
import { RelatorioProntuarioComponent } from './features/relatorio/relatorio-prontuario/relatorio-prontuario.component';
import { PacienteAnexosComponent } from './features/paciente/paciente-anexos/paciente-anexos.component';
import { ExameTelaComponent } from './features/exame/exame-tela/exame-tela.component';
import { ExameSaveComponent } from './features/exame/exame-save/exame-save.component';
import { ExameDetalhesComponent } from './features/exame/exame-detalhes/exame-detalhes.component';
import { ProfissionalContaExamesComponent } from './features/conta/profissional-conta/profissional-conta-exames/profissional-conta-exames.component';
import { ProfissionalContaExameSaveComponent } from './features/conta/profissional-conta/profissional-conta-exame-save/profissional-conta-exame-save.component';
import { AtendimentoPagamentoComponent } from './features/atendimento/atendimento-pagamento/atendimento-pagamento.component';
import { ProcedimentoTelaComponent } from './features/procedimento/procedimento-tela/procedimento-tela.component';
import { ProcedimentoDetalhesComponent } from './features/procedimento/procedimento-detalhes/procedimento-detalhes.component';
import { ProcedimentoSaveComponent } from './features/procedimento/procedimento-save/procedimento-save.component';
import { ProfissionalContaProcedimentosComponent } from './features/conta/profissional-conta/profissional-conta-procedimentos/profissional-conta-procedimentos.component';
import { ProfissionalContaProcedimentoSaveComponent } from './features/conta/profissional-conta/profissional-conta-procedimento-save/profissional-conta-procedimento-save.component';
import { AtendimentoRetornoComponent } from './features/atendimento/atendimento-retorno/atendimento-retorno.component';
import { SuporteContaDetalhesComponent } from './features/conta/suporte-conta/suporte-conta-detalhes/suporte-conta-detalhes.component';
import { SuporteContaAlterarComponent } from './features/conta/suporte-conta/suporte-conta-alterar/suporte-conta-alterar.component';

const routes: Routes = [
  { path: '', redirectTo:'login', pathMatch: 'full'},
  { path: 'login', component: LoginLayoutComponent, children: [
    { path: 'lg', component: LoginComponent, outlet: 'center' },
  ] },
  { path: 'app', component: AppLayoutComponent, children: [
    { path: 'home', component: HomeComponent, outlet: 'page'},

    { path: 'conta-alterar-senha', component: ContaAlterarSenhaComponent, outlet: 'page' },
    
    { path: 'profissional-conta-especialidades', component: ProfissionalContaEspecialidadesComponent, outlet: 'page' },
    { path: 'profissional-conta-especialidade-save', component: ProfissionalContaEspecialidadeSaveComponent, outlet: 'page' },
    { path: 'profissional-conta-exames', component: ProfissionalContaExamesComponent, outlet: 'page'},
    { path: 'profissional-conta-exame-save', component: ProfissionalContaExameSaveComponent, outlet: 'page' },
    { path: 'profissional-conta-procedimentos', component: ProfissionalContaProcedimentosComponent, outlet: 'page'},
    { path: 'profissional-conta-procedimento-save', component: ProfissionalContaProcedimentoSaveComponent, outlet: 'page' },

    { path: 'profissional-conta-detalhes', component: ProfissionalContaDetalhesComponent, outlet: 'page' },
    { path: 'recepcionista-conta-detalhes', component: RecepcionistaContaDetalhesComponent, outlet: 'page' },
    { path: 'diretor-conta-detalhes', component: DiretorContaDetalhesComponent, outlet: 'page' },
    { path: 'suporte-conta-detalhes', component: SuporteContaDetalhesComponent, outlet: 'page' },
    
    { path: 'profissional-conta-alterar', component: ProfissionalContaAlterarComponent, outlet: 'page'},
    { path: 'recepcionista-conta-alterar', component: RecepcionistaContaAlterarComponent, outlet: 'page'},
    { path: 'diretor-conta-alterar', component: DiretorContaAlterarComponent, outlet: 'page'},
    { path: 'suporte-conta-alterar', component: SuporteContaAlterarComponent, outlet: 'page'},

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
    { path: 'paciente-anamnese/:pacienteId/:anamneseCriada', component: PacienteAnamneseComponent, outlet: 'page' },
    { path: 'paciente-anexos/:pacienteId', component: PacienteAnexosComponent, outlet: 'page' },

    { path: 'atendimento-novo', component: AtendimentoNovoComponent, outlet: 'page' },
    { path: 'atendimento-tela', component: AtendimentoTelaComponent, outlet: 'page' },
    { path: 'atendimento-fila', component: AtendimentoFilaComponent, outlet: 'page' },
    { path: 'atendimento-fila-completa/:clinicaId/:profissionalId/:ano/:mes/:dia/:turno', component: AtendimentoFilaCompletaComponent, outlet: 'page' },
    { path: 'atendimento-iniciado', component: AtendimentoIniciadoComponent, outlet: 'page' },
    { path: 'atendimento-remarcar/:atendimentoId', component: AtendimentoRemarcarComponent, outlet: 'page' },
    { path: 'atendimento-retorno/:atendimentoId', component: AtendimentoRetornoComponent, outlet: 'page' },
    { path: 'atendimento-alterar/:atendimentoId', component: AtendimentoAlterarComponent, outlet: 'page' },
    { path: 'atendimento-detalhes/:atendimentoId', component: AtendimentoDetalhesComponent, outlet: 'page' },
    { path: 'atendimento-agenda', component: AtendimentoAgendaComponent, outlet: 'page' },
    { path: 'atendimento-pagamento/:atendimentoId', component: AtendimentoPagamentoComponent, outlet: 'page' },

    { path: 'lancamento-tela', component: LancamentoTelaComponent, outlet: 'page' },
    { path: 'lancamento-novo', component: LancamentoNovoComponent, outlet: 'page' },
    { path: 'lancamento-detalhes/:lancamentoId', component: LancamentoDetalhesComponent, outlet: 'page' },

    { path: 'anamnese-modelo-tela', component: AnamneseModeloTelaComponent, outlet: 'page' },
    { path: 'anamnese-modelo-detalhes/:id', component: AnamneseModeloDetalhesComponent, outlet: 'page' },
    { path: 'anamnese-modelo-save/:id', component: AnamneseModeloSaveComponent, outlet: 'page' },
    { path: 'anamnese-modelo-pergunta-save/:modeloId/:perguntaId', component: AnamneseModeloPerguntaSaveComponent, outlet: 'page' },

    { path: 'exame-tela', component: ExameTelaComponent, outlet: 'page' },
    { path: 'exame-detalhes/:exameId', component: ExameDetalhesComponent, outlet: 'page' },
    { path: 'exame-save/:exameId', component: ExameSaveComponent, outlet: 'page' },

    { path: 'procedimento-tela', component: ProcedimentoTelaComponent, outlet: 'page' },
    { path: 'procedimento-detalhes/:procedimentoId', component: ProcedimentoDetalhesComponent, outlet: 'page' },
    { path: 'procedimento-save/:procedimentoId', component: ProcedimentoSaveComponent, outlet: 'page' },

    { path: 'relatorio-balanco-dia', component: RelatorioBalancoDiaComponent, outlet: 'page' },
    { path: 'relatorio-prontuario', component: RelatorioProntuarioComponent, outlet: 'page' }
  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
