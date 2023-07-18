import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProcedimentoDetalhesComponent } from './procedimento-detalhes.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    ProcedimentoDetalhesComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatButtonModule
  ]
})
export class ProcedimentoDetalhesModule { }
