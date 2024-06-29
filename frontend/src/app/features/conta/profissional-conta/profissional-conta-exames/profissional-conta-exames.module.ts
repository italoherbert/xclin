import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfissionalContaExamesComponent } from './profissional-conta-exames.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { PaginatorModule } from 'src/app/shared/paginator/paginator.module';



@NgModule({
  declarations: [
    ProfissionalContaExamesComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    
    MatButtonModule,
    MatTableModule,

    PaginatorModule
  ]
})
export class ProfissionalContaExamesModule { }
