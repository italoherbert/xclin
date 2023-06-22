import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnamneseModeloRemoveDialog, AnamneseModeloTelaComponent } from './anamnese-modelo-tela.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';



@NgModule({
  declarations: [
    AnamneseModeloTelaComponent,
    AnamneseModeloRemoveDialog
  ],
  imports: [
    CommonModule,
    SharedModule,

    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule
  ]
})
export class AnamneseModeloTelaModule { }
