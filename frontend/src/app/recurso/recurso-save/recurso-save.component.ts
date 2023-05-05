import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { RecursoSave } from 'src/app/bean/recurso/recurso-save';
import { RecursoService } from 'src/app/service/recurso.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-recurso-save',
  templateUrl: './recurso-save.component.html',
  styleUrls: ['./recurso-save.component.css']
})
export class RecursoSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRotate : faRotate,
    faCircleLeft : faCircleLeft
  }

  recursoSave : RecursoSave = {
    nome: ''
  }

  constructor( private actRoute : ActivatedRoute, private recursoService: RecursoService, private sistemaService: SistemaService) {}

  ngOnInit() {    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( id !== '-1' ) {
      this.infoMsg = null;
      this.erroMsg = null;

      this.showSpinner = true;

      this.recursoService.getRecurso( id ).subscribe( {
        next: ( resp ) => {
          this.recursoSave.nome = resp.nome;
          this.showSpinner = false;
        },
        error: ( erro ) => {
          console.log( erro );
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    }
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    
    if ( id === '-1' ) { 
      this.recursoService.registraRecurso( this.recursoSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Recurso registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.recursoService.alteraRecurso( id, this.recursoSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Recurso alterado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

}
