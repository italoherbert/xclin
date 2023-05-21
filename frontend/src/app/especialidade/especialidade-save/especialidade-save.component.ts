import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { EspecialidadeSave } from 'src/app/bean/especialidade/especialidade-save';
import { EspecialidadeService } from 'src/app/service/especialidade.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-especialidade-save',
  templateUrl: './especialidade-save.component.html',
  styleUrls: ['./especialidade-save.component.css']
})
export class EspecialidadeSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRotate : faRotate,
    faCircleLeft : faCircleLeft
  }

  especialidadeSave : EspecialidadeSave = {
    nome: ''
  }

  constructor( 
    private actRoute : ActivatedRoute, 
    private especialidadeService: EspecialidadeService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( id !== '-1' ) {
      this.infoMsg = null;
      this.erroMsg = null;

      this.showSpinner = true;

      this.especialidadeService.getEspecialidade( id ).subscribe( {
        next: ( resp ) => {
          this.especialidadeSave.nome = resp.nome;
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
      this.especialidadeService.registraEspecialidade( this.especialidadeSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Especialidade registrada com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.especialidadeService.alteraEspecialidade( id, this.especialidadeSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Especialidade alterada com sucesso.";
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
