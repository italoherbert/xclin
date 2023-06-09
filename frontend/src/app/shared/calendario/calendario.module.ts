import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { SharedModule } from '../shared.module';
import { CalendarioComponent } from './calendario.component';



@NgModule({
  declarations: [
    CalendarioComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule
  ],
  exports: [
    CalendarioComponent
  ]
})
export class CalendarioModule { }
