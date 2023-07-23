import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { EspecialidadeSave } from 'src/app/core/bean/especialidade/especialidade-save';
import { EspecialidadeService } from 'src/app/core/service/especialidade.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

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

  clinicaId : number = 0;
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor( 
    private actRoute : ActivatedRoute, 
    private especialidadeService: EspecialidadeService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( id === '-1' ) {
      this.infoMsg = null;
      this.erroMsg = null;

      this.showSpinner = true;

      this.especialidadeService.loadRegTela().subscribe( {
        next: ( resp ) => {
          this.clinicasIDs = resp.clinicasIDs;
          this.clinicasNomes = resp.clinicasNomes;

          if ( this.clinicasIDs.length > 0 )
            this.clinicaId = this.clinicasIDs[ 0 ];

          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    } else {
      this.infoMsg = null;
      this.erroMsg = null;

      this.showSpinner = true;

      let id = this.actRoute.snapshot.paramMap.get( 'id' );      

      this.especialidadeService.loadEditTela( id ).subscribe( {
        next: ( resp ) => {
          this.clinicasIDs = [];
          this.clinicasNomes = [];
          
          this.clinicasIDs.push( resp.clinicaId );
          this.clinicasNomes.push( resp.clinicaNome );

          this.especialidadeSave.nome = resp.especialidade.nome;
          this.clinicaId = resp.clinicaId;

          this.showSpinner = false;
        },
        error: ( erro ) => {
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
      this.especialidadeService.registraEspecialidade( this.clinicaId, this.especialidadeSave ).subscribe({
        next: ( resp ) => {
          this.limpaForm();
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

  limpaForm() {
    this.especialidadeSave = {
      nome : ''
    }
  }

}
