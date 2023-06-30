import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { AtendimentoFilaCompletaComponent } from './atendimento-fila-completa.component';



@NgModule({
  declarations: [
    AtendimentoFilaCompletaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,    
  ]
})
export class AtendimentoFilaCompletaModule { }
