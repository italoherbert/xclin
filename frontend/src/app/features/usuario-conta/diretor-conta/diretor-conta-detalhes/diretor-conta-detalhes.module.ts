import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DiretorContaDetalhesComponent } from './diretor-conta-detalhes.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';



@NgModule({
  declarations: [
    DiretorContaDetalhesComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatButtonModule,
    MatChipsModule
  ]
})
export class DiretorContaDetalhesModule { }
