import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { ProfissionalContaDetalhesComponent } from './profissional-conta-detalhes.component';
import { MatChipsModule } from '@angular/material/chips';



@NgModule({
  declarations: [
    ProfissionalContaDetalhesComponent
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatCardModule,
    MatButtonModule,
    MatChipsModule
  ]
})
export class ProfissionalContaDetalhesModule { }
