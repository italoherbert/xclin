import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { ProcedimentoSave } from 'src/app/core/bean/procedimento/procedimento-save';
import { ProcedimentoService } from 'src/app/core/service/procedimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-procedimento-save',
  templateUrl: './procedimento-save.component.html',
  styleUrls: ['./procedimento-save.component.css']
})
export class ProcedimentoSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRotate : faRotate,
    faCircleLeft : faCircleLeft
  }

  procedimentoSave : ProcedimentoSave = {
    nome: '',
    descricao: ''
  }

  clinicaId : number = 0;
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor( 
    private actRoute : ActivatedRoute, 
    private procedimentoService: ProcedimentoService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let procedimentoId = this.actRoute.snapshot.paramMap.get( 'procedimentoId' );

    if ( procedimentoId === '-1' ) {
      this.procedimentoService.loadRegTela().subscribe({
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
      this.procedimentoService.loadEditTela( procedimentoId ).subscribe( {
        next: ( resp ) => {
          this.procedimentoSave = resp.procedimento;
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
    
    let procedimentoId = this.actRoute.snapshot.paramMap.get( 'procedimentoId' );
    
    if ( procedimentoId === '-1' ) { 
      this.procedimentoService.registra( this.clinicaId, this.procedimentoSave ).subscribe({
        next: ( resp ) => {
          this.limpaForm();
          this.infoMsg = "Procedimento registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.procedimentoService.altera( procedimentoId, this.procedimentoSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Procedimento alterado com sucesso.";
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
    this.procedimentoSave = {
      nome: '',
      descricao: ''
    }
  }

}

