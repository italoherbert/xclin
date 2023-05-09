import { Component } from '@angular/core';
import { faSave } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-diretor-clinica-vinculos',
  templateUrl: './diretor-clinica-vinculos.component.html',
  styleUrls: ['./diretor-clinica-vinculos.component.css']
})
export class DiretorClinicaVinculosComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave
  }

  vinculosObj : any = {
    diretor : {
      nome : ''
    }
  };

  grupoSelecionado( e : any, id : any ) {

  }

  salva() {

  }

}
