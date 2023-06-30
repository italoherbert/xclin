import { Component } from '@angular/core';
import { faCirclePlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-exame-tela',
  templateUrl: './exame-tela.component.html',
  styleUrls: ['./exame-tela.component.css']
})
export class ExameTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCirclePlus : faCirclePlus
  }

}
