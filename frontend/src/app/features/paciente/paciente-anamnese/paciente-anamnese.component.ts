import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faAnglesLeft, faAnglesRight, faEye, faSave } from '@fortawesome/free-solid-svg-icons';
import { AnamneseSave } from 'src/app/core/bean/anamnese/anamnese-save';
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
    faEye : faEye
  }

  anamneseSave : AnamneseSave = {
    tomaMedicamentos : false,
    quaisMedicamentos : '',
    temAlergias : false,
    quaisAlergias : '',
    pressaoArterial : '',
    temOuTeveProblemasDoCoracao : false,
    quaisProblemasDoCoracao : '',
    temFaltaDeAr : false,
    temDiabetes : false,
    sangramentoQuandoSeCorta : '',
    cicatrizacao : '',
    jaFezCirurgia : false,
    ehGestante : false,
    semanasDeGestacao : 0,
    problemasDeSaude : '',
    queixaPrincipal : '',
    jaTeveReacaoComAnestesiaDental : false,
    quaisReacoesTeveComAnestesiaDental: '',
    dataUltimoTratamentoDentario : '',
    senteDoresNosDentesOuGengiva : false,
    sangramentoNaGengiva : '',
    senteGostoRuimNaBoca : false,
    quantasVezesEscovaOsDentes : 3,
    usaFioDental : false,
    senteDoresNoMaxilarOuOuvido : false,
    rangeOsDentes : false,
    teveFeridaNaFaceOuNosLabios : false,
    fuma : false,
    quantidadeQueFuma : '',
  }

  pressaoArterialTipos : any[] = [];
  cicatrizacaoTipos : any[] = [];
  sangramentoTipos : any[] = [];
  sangramentoNaGengivaTipos : any[] = [];

  pacienteNome : string = '';
  anamnesePreenchida : boolean = false;

  constructor( 
    private actRoute : ActivatedRoute,
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

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'id' );

    this.anamneseService.getEditAnamnese( pacienteId ).subscribe({
      next: ( resp ) => {
        this.pressaoArterialTipos = resp.pressaoArterialTipos;
        this.cicatrizacaoTipos = resp.cicatrizacaoTipos;
        this.sangramentoTipos = resp.sangramentoTipos;
        this.sangramentoNaGengivaTipos = resp.sangramentoNaGengivaTipos;
        this.pacienteNome = resp.pacienteNome;
        this.anamnesePreenchida = resp.anamnesePreenchida;

        if ( this.pressaoArterialTipos.length > 0 )
          this.anamneseSave.pressaoArterial = this.pressaoArterialTipos[ 0 ].name;

        if (this.cicatrizacaoTipos.length > 0 )
          this.anamneseSave.cicatrizacao = this.cicatrizacaoTipos[ 0 ].name;

        if ( this.sangramentoTipos.length > 0 )
          this.anamneseSave.sangramentoQuandoSeCorta = this.sangramentoTipos[ 0 ].name;

        if ( this.sangramentoNaGengivaTipos.length > 0 )
          this.anamneseSave.sangramentoNaGengiva = this.sangramentoNaGengivaTipos[ 0 ].name;
        
        if ( this.anamnesePreenchida === true )
          this.anamneseSave = resp.anamnese;     
        
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  salva() {    
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'id' );    

    this.anamneseService.salvaAnamnese( pacienteId, this.anamneseSave ).subscribe({
      next: (resp) => {
        this.anamnesePreenchida = true;
        this.infoMsg = "Anamnese salva com sucesso.";        
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

  downloadRelatorio() {
    this.infoMsg = null;
    this.erroMsg = null;

    if ( this.anamnesePreenchida == false ) {
      this.infoMsg = "A anamnese do paciente não foi preenchida ainda.";
      return;
    }

    this.showSpinner = true;

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'id' );

    this.relatorioService.getRelatorioAnamnese( pacienteId ).subscribe({
      next: (resp) => {
        this.sistemaService.criaDownloadAncora( resp, 'anamnese.pdf' );
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });  
  }

}
