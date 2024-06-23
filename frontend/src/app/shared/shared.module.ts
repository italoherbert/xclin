import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { PaginatorComponent } from './paginator/paginator.component';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    RouterModule,    
    HttpClientModule,
    FormsModule,

    FontAwesomeModule,

    BrowserAnimationsModule
  ],
  exports: [
    RouterModule,
    HttpClientModule,
    FormsModule,

    FontAwesomeModule,

    BrowserAnimationsModule
  ]
})
export class SharedModule { }
