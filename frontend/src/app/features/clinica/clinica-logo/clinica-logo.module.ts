import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ClinicaLogoComponent } from './clinica-logo.component';



@NgModule({
  declarations: [ 
    ClinicaLogoComponent 
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,    
    MatInputModule,   
    MatButtonModule,
    MatIconModule
  ]
})
export class ClinicaLogoModule { }
