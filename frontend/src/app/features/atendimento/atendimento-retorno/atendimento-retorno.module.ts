import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AtendimentoRetornoComponent } from './atendimento-retorno.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatButtonModule } from '@angular/material/button';
import { CalendarioModule } from 'src/app/shared/calendario/calendario.module';



@NgModule({
  declarations: [
    AtendimentoRetornoComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    CalendarioModule,

    MatButtonModule
  ]
})
export class AtendimentoRetornoModule { }
