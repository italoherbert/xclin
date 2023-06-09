import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { EspecialidadeDetalhesComponent } from './especialidade-detalhes.component';



@NgModule({
  declarations: [
    EspecialidadeDetalhesComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatButtonModule
  ]
})
export class EspecialidadeDetalhesModule { }
