import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DiretorContaAlterarComponent } from './diretor-conta-alterar.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';



@NgModule({
  declarations: [
    DiretorContaAlterarComponent
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
export class DiretorContaAlterarModule { }
