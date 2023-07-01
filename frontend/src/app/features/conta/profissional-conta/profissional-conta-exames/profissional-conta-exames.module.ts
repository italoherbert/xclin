import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfissionalContaExamesComponent } from './profissional-conta-exames.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    ProfissionalContaExamesComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    
    MatButtonModule
  ]
})
export class ProfissionalContaExamesModule { }
