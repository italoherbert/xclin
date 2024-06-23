import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared.module';
import { MatPaginatorModule } from '@angular/material/paginator';
import { PaginatorComponent } from './paginator.component';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  declarations: [
    PaginatorComponent
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatTableModule,
    MatPaginatorModule
  ],
  exports: [
    PaginatorComponent
  ]
})
export class PaginatorModule { }
