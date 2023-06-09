import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaAlterarSenhaComponent } from './conta-alterar-senha.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    ContaAlterarSenhaComponent
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
export class ContaAlterarSenhaModule { }
