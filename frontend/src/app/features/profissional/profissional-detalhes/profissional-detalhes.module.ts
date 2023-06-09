import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { ProfissionalDetalhesComponent } from './profissional-detalhes.component';
import { MatChipsModule } from '@angular/material/chips';



@NgModule({
  declarations: [
    ProfissionalDetalhesComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatButtonModule,
    MatChipsModule
  ]
})
export class ProfissionalDetalhesModule { }
