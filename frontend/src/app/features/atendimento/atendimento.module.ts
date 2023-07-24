import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AtendimentoAlterarModule } from './atendimento-alterar/atendimento-alterar.module';
import { AtendimentoIniciadoModule } from './atendimento-iniciado/atendimento-iniciado.module';
import { AtendimentoDetalhesModule } from './atendimento-detalhes/atendimento-detalhes.module';
import { AtendimentoFilaModule } from './atendimento-fila/atendimento-fila.module';
import { AtendimentoNovoModule } from './atendimento-novo/atendimento-novo.module';
import { AtendimentoRemarcarModule } from './atendimento-remarcar/atendimento-remarcar.module';
import { AtendimentoFiltroModule } from './atendimento-filtro/atendimento-filtro.module';
import { AtendimentoAgendaModule } from './atendimento-agenda/atendimento-agenda.module';
import { AtendimentoFilaCompletaModule } from './atendimento-fila-completa/atendimento-fila-completa.module';
import { AtendimentoPagamentoModule } from './atendimento-pagamento/atendimento-pagamento.module';
import { AtendimentoRetornoModule } from './atendimento-retorno/atendimento-retorno.module';


@NgModule({
  declarations: [ 
  
  ],
  imports: [
    CommonModule,
    
    AtendimentoAlterarModule,
    AtendimentoIniciadoModule,
    AtendimentoDetalhesModule,
    AtendimentoFilaModule,
    AtendimentoNovoModule,
    AtendimentoRemarcarModule,
    AtendimentoRetornoModule,
    AtendimentoFiltroModule,
    AtendimentoAgendaModule,
    AtendimentoFilaCompletaModule,
    AtendimentoPagamentoModule
  ]
})
export class AtendimentoModule { }
