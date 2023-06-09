import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConsultaAlterarModule } from './consulta-alterar/consulta-alterar.module';
import { ConsultaAtendimentoModule } from './consulta-atendimento/consulta-atendimento.module';
import { ConsultaDetalhesModule } from './consulta-detalhes/consulta-detalhes.module';
import { ConsultaFilaModule } from './consulta-fila/consulta-fila.module';
import { ConsultaNovaModule } from './consulta-nova/consulta-nova.module';
import { ConsultaRemarcarModule } from './consulta-remarcar/consulta-remarcar.module';
import { ConsultaTelaModule } from './consulta-tela/consulta-tela.module';


@NgModule({
  declarations: [ 
  ],
  imports: [
    CommonModule,
    
    ConsultaAlterarModule,
    ConsultaAtendimentoModule,
    ConsultaDetalhesModule,
    ConsultaFilaModule,
    ConsultaNovaModule,
    ConsultaRemarcarModule,
    ConsultaTelaModule
  ]
})
export class ConsultaModule { }
