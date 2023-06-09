import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatChipsModule } from '@angular/material/chips';
import { DiretorClinicaVinculosComponent } from './diretor-clinica-vinculos.component';
import { MatIconModule } from '@angular/material/icon';



@NgModule({
  declarations: [
    DiretorClinicaVinculosComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatChipsModule,
    MatIconModule
  ]
})
export class DiretorClinicaVinculosModule { }
