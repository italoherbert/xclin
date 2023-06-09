import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatButtonModule } from '@angular/material/button';
import { CalendarioModule } from 'src/app/shared/calendario/calendario.module';
import { ConsultaRemarcarComponent } from './consulta-remarcar.component';



@NgModule({
  declarations: [
    ConsultaRemarcarComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    CalendarioModule,

    MatButtonModule
  ]
})
export class ConsultaRemarcarModule { }
