import { Component } from '@angular/core';

@Component({
  selector: 'app-profissional-conta-especialidade-save',
  templateUrl: './profissional-conta-especialidade-save.component.html',
  styleUrls: ['./profissional-conta-especialidade-save.component.css']
})
export class ProfissionalContaEspecialidadeSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {

  }

  especialidadesIDs : number[] = [];
  especialidadesNomes : string[] = [];

  ngOnInit() {
        
  }

}
