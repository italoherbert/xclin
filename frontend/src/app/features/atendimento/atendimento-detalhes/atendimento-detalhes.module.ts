import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AtendimentoDetalhesComponent } from './atendimento-detalhes.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';



@NgModule({
  declarations: [
    AtendimentoDetalhesComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatCheckboxModule,
    MatButtonModule
  ]
})
export class AtendimentoDetalhesModule { }
