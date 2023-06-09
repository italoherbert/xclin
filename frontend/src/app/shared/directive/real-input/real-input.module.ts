import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RealInputDirective } from './real-input.directive';



@NgModule({
  declarations: [
    RealInputDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    RealInputDirective
  ]
})
export class RealInputModule { }
