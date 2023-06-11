import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { ConsultaAgendaComponent } from './consulta-agenda.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { CalendarioModule } from 'src/app/shared/calendario/calendario.module';
import { MatInputModule } from '@angular/material/input';



@NgModule({
  declarations: [
    ConsultaAgendaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    CalendarioModule,

    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule
  ]
})
export class ConsultaAgendaModule { }
