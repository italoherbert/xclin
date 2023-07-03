import { Component } from '@angular/core';
import { faCirclePause, faCirclePlay, faClockRotateLeft, faEye, faSave } from '@fortawesome/free-solid-svg-icons';
import { Atendimento } from 'src/app/core/bean/atendimento/atendimento';
import { AtendimentoObservacoes } from 'src/app/core/bean/atendimento/atendimento-observacoes';
import { AtendimentoObservacoesAlter } from 'src/app/core/bean/atendimento/atendimento-observacoes-alter';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { RelatorioService } from 'src/app/core/service/relatorio.service';
import { PacienteAnexoService } from 'src/app/core/service/paciente-anexo.service';

@Component({
  selector: 'app-atendimento-iniciado',
  templateUrl: './atendimento-iniciado.component.html',
  styleUrls: ['./atendimento-iniciado.component.css']
})
export class AtendimentoIniciadoComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  obsInfoMsg : any = null;
  obsErroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCirclePlay : faCirclePlay,
    faCirclePause : faCirclePause,
    faSave : faSave,
    faClockRotateLeft : faClockRotateLeft,
    faEye : faEye
  }

  atendimento : Atendimento = {
    id : 0,
    observacoes: '',
    turno: '',
    dataAtendimento: '',
    pago: false,
    valorTotal: 0,
    valorPago: 0,
    status: '',
    pacienteId: 0,
    pacienteNome: '',
    pacienteAnamneseCriada : false,
    profissionalId: 0,
    profissionalNome: '',
    clinicaId: 0,
    clinicaNome: '',
    statusLabel: '',
    turnoLabel: '',
    temConsulta: false,
    consulta: {
      id: 0,
      especialidadeId: 0,
      especialidadeNome: '',
      valor: 0,
      retorno: false
    },
    exames: []
  }

  observacoesSave : AtendimentoObservacoesAlter = {
    observacoes : ''
  }

  historicoObservacoes : AtendimentoObservacoes[] = [
    { observacoes: '', dataSaveObservacoes: '' }
  ];
  historicoObservacoesPageSize : number = 10;
  quantPacientesNaFila: number = 0;

  clinicaId : number = 0;
  turno: string = '';

  pacienteAnexos : any[] = [];

  turnos : any[] = [];

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  carregarAtivado : boolean = false;

  constructor(
    private atendimentoService : AtendimentoService,
    private pacienteAnexoService : PacienteAnexoService,
    private relatorioService : RelatorioService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.atendimentoService.getIniciadaTela().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;
        this.turnos = resp.turnos;

        if ( this.clinicasIDs.length > 0 )
          this.clinicaId = this.clinicasIDs[ 0 ];

        if ( this.turnos.length > 0 )
          this.turno = this.turnos[ 0 ].name;
        
        this.carregaAtendimentoIniciada();

        this.showSpinner = false;               
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  carregaAtendimentoIniciada() {
    if ( this.turno == '' ) {
      this.erroMsg = 'Selecione o turno.';
      return;
    }

    this.atendimentoService.getAtendimentoIniciado( 
          this.clinicaId, 
          this.turno, 
          this.historicoObservacoesPageSize ).subscribe({

      next: (resp) => {
        this.quantPacientesNaFila = resp.quantPacientesNaFila;

        this.erroMsg = null;

        if ( resp.atendimentoIniciado == true ) {
          this.historicoObservacoes = resp.historicoObservacoes;

          if ( resp.atendimento.id !== this.atendimento.id ) {
            this.atendimento = resp.atendimento;
            this.pacienteAnexos = resp.pacienteAnexos;
            this.observacoesSave.observacoes = resp.atendimento.observacoes;
          }

          this.infoMsg = null;
        } else {
          this.infoMsg = "Nenhuma atendimento iniciado por enquanto."
        }
                
        setTimeout( () => {
          this.carregaAtendimentoIniciada();
        }, 5000 );        
      },
      error: (erro) => {
        this.infoMsg = null;
        this.erroMsg = this.sistemaService.mensagemErro( erro );
      }
    });
  }

  salvaObservacao() {
    this.obsInfoMsg = null;
    this.obsErroMsg = null;

    this.showSpinner = true;

    this.atendimentoService.alteraObservacoes( this.atendimento.id, this.observacoesSave ).subscribe({
      next: (resp) => {
        this.obsInfoMsg = "Observações alteradas com sucesso.";
        this.showSpinner = false;
      },
      error: (erro) => {
        this.obsErroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  geraRelatorioProntuario() {
    this.infoMsg = null;
    this.erroMsg = null;

    if ( this.atendimento.pacienteAnamneseCriada == false ) {
      this.infoMsg = "A anamnese do paciente não foi criada ainda.";
      return;
    }

    this.showSpinner = true;

    let pacienteId = this.atendimento.pacienteId;

    this.relatorioService.getRelatorioProntuario( pacienteId ).subscribe({
      next: (resp) => {
        this.sistemaService.criaDownloadAncora( resp, 'prontuario.pdf' );
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    }); 
  }

  geraDownloadAnexo( anexoId : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.pacienteAnexoService.getArquivo( anexoId ).subscribe({
      next: (resp) => {        
        this.sistemaService.base64ToDownload( resp.arquivo, resp.nome );
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
