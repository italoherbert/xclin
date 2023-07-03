import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AtendimentoPagamentoComponent } from './atendimento-pagamento.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { RealInputModule } from 'src/app/shared/directive/real-input/real-input.module';



@NgModule({
  declarations: [
    AtendimentoPagamentoComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    RealInputModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class AtendimentoPagamentoModule { }
