import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Recurso } from 'src/app/bean/recurso/recurso';
import { RecursoService } from 'src/app/service/recurso.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-recurso-detalhes',
  templateUrl: './recurso-detalhes.component.html',
  styleUrls: ['./recurso-detalhes.component.css']
})
export class RecursoDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  recurso : Recurso = {
    id : '',
    nome: ''
  }

  constructor( private actRoute : ActivatedRoute, private recursoService: RecursoService, private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.recursoService.getRecurso( id ).subscribe({
      next: ( resp ) => {
        this.recurso = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
