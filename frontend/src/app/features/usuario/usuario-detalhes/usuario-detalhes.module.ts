import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { SharedModule } from 'src/app/shared/shared.module';
import { UsuarioDetalhesComponent } from './usuario-detalhes.component';



@NgModule({
  declarations: [
    UsuarioDetalhesComponent
  ],
  imports: [
    CommonModule,

    SharedModule,

    MatCardModule,
    MatButtonModule,
  ]
})
export class UsuarioDetalhesModule { }
