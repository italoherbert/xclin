import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConsultaAtendimentoComponent } from './consulta-atendimento.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';



@NgModule({
  declarations: [
    ConsultaAtendimentoComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule
  ]
})
export class ConsultaAtendimentoModule { }
