import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { ConsultaFilaCompletaComponent } from './consulta-fila-completa.component';



@NgModule({
  declarations: [
    ConsultaFilaCompletaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,    
  ]
})
export class ConsultaFilaCompletaModule { }
