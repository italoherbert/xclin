import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { ExameSave } from 'src/app/core/bean/exame/exame-save';
import { ExameService } from 'src/app/core/service/exame.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-exame-save',
  templateUrl: './exame-save.component.html',
  styleUrls: ['./exame-save.component.css']
})
export class ExameSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRotate : faRotate,
    faCircleLeft : faCircleLeft
  }

  exameSave : ExameSave = {
    nome: '',
    descricao: ''
  }

  clinicaId : number = 0;
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor( 
    private actRoute : ActivatedRoute, 
    private exameService: ExameService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let exameId = this.actRoute.snapshot.paramMap.get( 'exameId' );

    if ( exameId === '-1' ) {
      this.exameService.loadRegTela().subscribe({
        next: (resp) => {
          this.clinicasIDs = resp.clinicasIDs;
          this.clinicasNomes = resp.clinicasNomes;

          if ( this.clinicasIDs.length > 0 )
            this.clinicaId = this.clinicasIDs[ 0 ];

          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {      
      this.exameService.loadEditTela( exameId ).subscribe( {
        next: ( resp ) => {
          this.exameSave = resp.exame;
          this.clinicasIDs = [];
          this.clinicasNomes = [];

          this.clinicasIDs.push( resp.clinicaId );
          this.clinicasNomes.push( resp.clinicaNome );

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
    
    let exameId = this.actRoute.snapshot.paramMap.get( 'exameId' );
    
    if ( exameId === '-1' ) { 
      this.exameService.registra( this.clinicaId, this.exameSave ).subscribe({
        next: ( resp ) => {
          this.limpaForm();
          this.infoMsg = "Exame registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.exameService.altera( exameId, this.exameSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Exame alterado com sucesso.";
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
    this.exameSave = {
      nome: '',
      descricao: ''
    }
  }

}
