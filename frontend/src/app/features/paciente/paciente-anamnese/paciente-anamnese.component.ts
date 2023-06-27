import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { faAnglesLeft, faAnglesRight, faEye, faLink, faSave } from '@fortawesome/free-solid-svg-icons';
import { AnamneseModelo } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo';
import { Anamnese } from 'src/app/core/bean/anamnese/anamnese';
import { AnamneseService } from 'src/app/core/service/anamnese.service';
import { RelatorioService } from 'src/app/core/service/relatorio.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-paciente-anamnese',
  templateUrl: './paciente-anamnese.component.html',
  styleUrls: ['./paciente-anamnese.component.css']
})
export class PacienteAnamneseComponent {

  erroMsg : any = null;
  infoMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faAnglesLeft : faAnglesLeft,
    faAnglesRight : faAnglesRight,
    faEye : faEye,
    faLink : faLink
  }

  anamnese : Anamnese = {
    id: 0,
    nome: '',
    dataCriacao: '',
    perguntas : []
  }

  anamneseModeloId : number = 0;
  anamneseModelosIDs : number[] = [];
  anamneseModelosNomes : number[] = [];

  pacienteNome : string = '';

  constructor( 
    private actRoute : ActivatedRoute,
    private matDialog : MatDialog,
    private anamneseService: AnamneseService, 
    private relatorioService: RelatorioService,
    private sistemaService: SistemaService ) {}

  ngOnInit() {
    this.carrega();
  }
  
  carrega() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'pacienteId' );
    let anamneseCriada = this.actRoute.snapshot.paramMap.get( 'anamneseCriada' );

    if ( anamneseCriada == 'false' ) {
      this.anamneseService.loadRegTela( pacienteId ).subscribe({
        next: ( resp ) => {
          this.anamneseModelosIDs = resp.anamneseModelos.ids;
          this.anamneseModelosNomes = resp.anamneseModelos.nomes;
          this.pacienteNome = resp.pacienteNome;
         
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {      
      this.anamneseService.loadEditTela( pacienteId ).subscribe({
        next: ( resp ) => {
          this.anamnese = resp.anamnese;
          this.anamneseModelosIDs = resp.anamneseModelos.ids;
          this.anamneseModelosNomes = resp.anamneseModelos.nomes;
          this.pacienteNome = resp.pacienteNome;

          this.carregaAnamnesePerguntas();
         
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

  salva() {    
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'pacienteId' );    

    this.anamneseService.altera( pacienteId, this.anamnese ).subscribe({
      next: (resp) => { 
        this.infoMsg = "Anamnese salva com sucesso.";
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
      
  }

  carregaAnamnesePerguntas() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'pacienteId' );    

    this.anamneseService.get( pacienteId ).subscribe({
      next: (resp) => {
        this.anamnese = resp;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  vinculaModelo() {
    let anamneseCriada = this.actRoute.snapshot.paramMap.get( 'anamneseCriada' );

    if ( anamneseCriada == 'true' ) {
      this.mostraVinculoConfirmDialog();
    } else {
      this.vinculaModeloSeConfirmado();
    }

  }

  vinculaModeloSeConfirmado() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'pacienteId' );    

    this.anamneseService.vinculaModelo( pacienteId, this.anamneseModeloId ).subscribe({
      next: (resp) => {
        this.carregaAnamnesePerguntas();
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  mostraVinculoConfirmDialog() {
    let dialogRef = this.matDialog.open(PacienteAnamneseVinculaModeloDialog );
    dialogRef.afterClosed().subscribe( (result) => {
      if ( result === true )
        this.vinculaModeloSeConfirmado();
    } );
    
  }

}


@Component({
  selector: 'paciente-anamnese-vincula-modelo-dialog',
  templateUrl: 'paciente-anamnese-vincula-modelo-dialog.html',
})
export class PacienteAnamneseVinculaModeloDialog {

}

