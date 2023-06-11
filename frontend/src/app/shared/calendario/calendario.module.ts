import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { SharedModule } from '../shared.module';
import { CalendarioComponent } from './calendario.component';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    CalendarioComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule
  ],
  exports: [
    CalendarioComponent
  ]
})
export class CalendarioModule { }
