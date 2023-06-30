import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { SharedModule } from 'src/app/shared/shared.module';
import { RecepcionistaContaAlterarComponent } from './recepcionista-conta-alterar.component';



@NgModule({
  declarations: [
    RecepcionistaContaAlterarComponent
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class RecepcionistaContaAlterarModule { }
