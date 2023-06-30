import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AtendimentoAlterarComponent } from './atendimento-alterar.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatCheckboxModule } from '@angular/material/checkbox';



@NgModule({
  declarations: [
    AtendimentoAlterarComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    MatCheckboxModule
  ]
})
export class AtendimentoAlterarModule { }
