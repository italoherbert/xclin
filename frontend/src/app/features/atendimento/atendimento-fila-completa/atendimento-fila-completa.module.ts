import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { AtendimentoFilaCompletaComponent } from './atendimento-fila-completa.component';
import { MatTableModule } from '@angular/material/table';
import { PaginatorModule } from 'src/app/shared/paginator/paginator.module';



@NgModule({
  declarations: [
    AtendimentoFilaCompletaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,   
    
    MatTableModule,

    PaginatorModule
  ]
})
export class AtendimentoFilaCompletaModule { }
