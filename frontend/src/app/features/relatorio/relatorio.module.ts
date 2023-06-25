import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RelatorioBalancoDiaModule } from './relatorio-balanco-dia/relatorio-balanco-dia.module';
import { RelatorioProntuarioComponent } from './relatorio-prontuario/relatorio-prontuario.component';
import { RelatorioProntuarioModule } from './relatorio-prontuario/relatorio-prontuario.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    RelatorioBalancoDiaModule,
    RelatorioProntuarioModule
  ]
})
export class RelatorioModule { }
