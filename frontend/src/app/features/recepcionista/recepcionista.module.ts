import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NaoAdminRecepcionistaTelaModule } from './nao-admin-recepcionista-tela/nao-admin-recepcionista-tela.module';
import { RecepcionistaTelaModule } from './recepcionista-tela/recepcionista-tela.module';
import { RecepcionistaDetalhesModule } from './recepcionista-detalhes/recepcionista-detalhes.module';
import { RecepcionistaSaveModule } from './recepcionista-save/recepcionista-save.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    NaoAdminRecepcionistaTelaModule,
    RecepcionistaTelaModule,
    RecepcionistaDetalhesModule,
    RecepcionistaSaveModule
  ]
})
export class RecepcionistaModule { }
