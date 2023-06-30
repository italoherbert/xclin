import { Component } from '@angular/core';

@Component({
  selector: 'app-exame-novo',
  templateUrl: './exame-novo.component.html',
  styleUrls: ['./exame-novo.component.css']
})
export class ExameNovoComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  
}
