import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Recepcionista } from 'src/app/bean/recepcionista/recepcionista';
import { RecepcionistaService } from 'src/app/service/recepcionista.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-recepcionista-detalhes',
  templateUrl: './recepcionista-detalhes.component.html',
  styleUrls: ['./recepcionista-detalhes.component.css']
})
export class RecepcionistaDetalhesComponent {

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
    private actRoute : ActivatedRoute, 
    private recepcionistaService: RecepcionistaService, 
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( this.sistemaService.isAdminEscopo() === true ) {
      this.recepcionistaService.getRecepcionista( id ).subscribe({
        next: ( resp ) => {
          this.recepcionistaDetalhes = resp;
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.recepcionistaService.getRecepcionistaNaoAdmin( id ).subscribe({
        next: (resp) => {
          this.recepcionistaDetalhes = resp;
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });      
    }
  }
  
}
