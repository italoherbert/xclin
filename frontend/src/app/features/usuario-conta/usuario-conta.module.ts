import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaAlterarSenhaModule } from './conta-alterar-senha/conta-alterar-senha.module';
import { DiretorContaModule } from './diretor-conta/diretor-conta.module';
import { ProfissionalContaModule } from './profissional-conta/profissional-conta.module';
import { RecepcionistaContaModule } from './recepcionista-conta/recepcionista-conta.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    ContaAlterarSenhaModule,
    DiretorContaModule,
    ProfissionalContaModule,
    RecepcionistaContaModule
  ]
})
export class UsuarioContaModule { }
