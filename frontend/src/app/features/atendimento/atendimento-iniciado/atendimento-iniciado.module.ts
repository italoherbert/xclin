import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AtendimentoIniciadoComponent } from './atendimento-iniciado.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';



@NgModule({
  declarations: [
    AtendimentoIniciadoComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule,
    MatListModule
  ]
})
export class AtendimentoIniciadoModule { }
