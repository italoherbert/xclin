import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NaoAdminClinicaTelaComponent } from './nao-admin-clinica-tela.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';



@NgModule({
  declarations: [
    NaoAdminClinicaTelaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule
  ]
})
export class NaoAdminClinicaTelaModule { }
