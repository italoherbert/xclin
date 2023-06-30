import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExameTelaComponent } from './exame-tela.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    ExameTelaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class ExameTelaModule { }
