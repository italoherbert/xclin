import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { ClinicaExameSave } from 'src/app/core/bean/clinica-exame/clinica-exame-save';
import { ClinicaExameService } from 'src/app/core/service/clinica-exame.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-clinica-exame-save',
  templateUrl: './clinica-exame-save.component.html',
  styleUrls: ['./clinica-exame-save.component.css']
})
export class ClinicaExameSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRotate : faRotate,
    faCircleLeft : faCircleLeft
  }

  exameSave : ClinicaExameSave = {
    nome: '',
    descricao: '',
    valor: 0
  }

  clinicaId : number = 0;
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor( 
    private actRoute : ActivatedRoute, 
    private clinicaExameService: ClinicaExameService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let exameId = this.actRoute.snapshot.paramMap.get( 'clinicaExameId' );

    if ( exameId === '-1' ) {
      this.clinicaExameService.loadRegTela().subscribe({
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
      this.clinicaExameService.loadEditTela( exameId ).subscribe( {
        next: ( resp ) => {
          this.exameSave = resp.exame;
          this.clinicasIDs = resp.clinicasIDs;
          this.clinicasNomes = resp.clinicasNomes;

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
    
    let exameId = this.actRoute.snapshot.paramMap.get( 'clinicaExameId' );
    
    if ( exameId === '-1' ) { 
      this.clinicaExameService.registra( this.clinicaId, this.exameSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Exame registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.clinicaExameService.altera( exameId, this.exameSave ).subscribe({
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

}
