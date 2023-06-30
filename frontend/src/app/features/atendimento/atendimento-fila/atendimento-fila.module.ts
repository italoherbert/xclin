import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule, MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { AtendimentoFilaComponent } from './atendimento-fila.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMenuModule } from '@angular/material/menu';



@NgModule({
  declarations: [
    AtendimentoFilaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatMenuModule
  ]
})
export class AtendimentoFilaModule { }
