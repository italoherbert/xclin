import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { NaoAdminRecepcionistaTelaComponent } from './nao-admin-recepcionista-tela.component';



@NgModule({
  declarations: [
    NaoAdminRecepcionistaTelaComponent
  ],
  imports: [
    CommonModule,

    SharedModule,
    
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule
  ]
})
export class NaoAdminRecepcionistaTelaModule { }
