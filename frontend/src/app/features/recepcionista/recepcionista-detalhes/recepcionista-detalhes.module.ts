import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { RecepcionistaDetalhesComponent } from './recepcionista-detalhes.component';



@NgModule({
  declarations: [
    RecepcionistaDetalhesComponent
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatCardModule,
    MatButtonModule
  ]
})
export class RecepcionistaDetalhesModule { }
