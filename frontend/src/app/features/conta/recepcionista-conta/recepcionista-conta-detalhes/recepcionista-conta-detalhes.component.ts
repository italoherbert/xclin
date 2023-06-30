import { Component } from '@angular/core';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Recepcionista } from 'src/app/core/bean/recepcionista/recepcionista';
import { ContaRecepcionistaService } from 'src/app/core/service/conta-recepcionista.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-recepcionista-conta-detalhes',
  templateUrl: './recepcionista-conta-detalhes.component.html',
  styleUrls: ['./recepcionista-conta-detalhes.component.css']
})
export class RecepcionistaContaDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  recepcionistaDetalhes : Recepcionista = {
    id: 0,
    nome : '',
    clinicaId : 0,
    clinicaNome : '',
    usuario: {
      id: 0,
      username: '',
      perfil: '',
      perfilLabel: ''
    }    
  }

  constructor( 
    private contaRecepcionistaService: ContaRecepcionistaService, 
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.contaRecepcionistaService.get().subscribe({
      next: ( resp ) => {
        this.recepcionistaDetalhes = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
