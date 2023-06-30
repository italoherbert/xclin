import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfissionalContaEspecialidadesComponent } from './profissional-conta-especialidades.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    ProfissionalContaEspecialidadesComponent
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatButtonModule
  ]
})
export class ProfissionalContaEspecialidadesModule { }
