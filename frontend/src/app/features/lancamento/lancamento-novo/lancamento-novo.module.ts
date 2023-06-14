import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { LancamentoNovoComponent } from './lancamento-novo.component';
import { MatCardModule } from '@angular/material/card';
import { RealInputModule } from 'src/app/shared/directive/real-input/real-input.module';



@NgModule({
  declarations: [
    LancamentoNovoComponent
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
    
    RealInputModule
  ]
})
export class LancamentoNovoModule { }
