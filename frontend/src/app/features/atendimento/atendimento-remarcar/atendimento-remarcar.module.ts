import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatButtonModule } from '@angular/material/button';
import { CalendarioModule } from 'src/app/shared/calendario/calendario.module';
import { AtendimentoRemarcarComponent } from './atendimento-remarcar.component';



@NgModule({
  declarations: [
    AtendimentoRemarcarComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    CalendarioModule,

    MatButtonModule
  ]
})
export class AtendimentoRemarcarModule { }
