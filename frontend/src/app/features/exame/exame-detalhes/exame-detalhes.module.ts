import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExameDetalhesComponent } from './exame-detalhes.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    ExameDetalhesComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatButtonModule
  ]
})
export class ExameDetalhesModule { }
