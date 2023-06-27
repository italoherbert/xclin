import { Component } from '@angular/core';
import { faCirclePause, faCirclePlay, faClockRotateLeft, faEye, faSave } from '@fortawesome/free-solid-svg-icons';
import { Consulta } from 'src/app/core/bean/consulta/consulta';
import { ConsultaObservacoes } from 'src/app/core/bean/consulta/consulta-observacoes';
import { ConsultaObservacoesAlter } from 'src/app/core/bean/consulta/consulta-observacoes-alter';
import { ConsultaService } from 'src/app/core/service/consulta.service';
import { AnamneseService } from 'src/app/core/service/anamnese.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { RelatorioService } from 'src/app/core/service/relatorio.service';
import { PacienteAnexoService } from 'src/app/core/service/paciente-anexo.service';

@Component({
  selector: 'app-consulta-atendimento',
  templateUrl: './consulta-atendimento.component.html',
  styleUrls: ['./consulta-atendimento.component.css']
})
export class ConsultaAtendimentoComponent {

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

  consulta : Consulta = {
    id : 0,
    observacoes: '',
    turno: '',
    dataAtendimento: '',
    paga: false,
    retorno: false,
    valor: 0,
    status: '',
    pacienteId: 0,
    pacienteNome: '',
    pacienteAnamneseCriada : false,
    profissionalId: 0,
    profissionalNome: '',
    clinicaId: 0,
    clinicaNome: '',
    especialidadeId: 0,
    especialidadeNome: '',
    statusLabel: '',
    turnoLabel: ''
  }

  observacoesSave : ConsultaObservacoesAlter = {
    observacoes : ''
  }

  historicoObservacoes : ConsultaObservacoes[] = [
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
    private consultaService : ConsultaService,
    private pacienteAnexoService : PacienteAnexoService,
    private relatorioService : RelatorioService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.consultaService.getIniciadaTela().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;
        this.turnos = resp.turnos;

        if ( this.clinicasIDs.length > 0 )
          this.clinicaId = this.clinicasIDs[ 0 ];

        if ( this.turnos.length > 0 )
          this.turno = this.turnos[ 0 ].name;
        
        this.carregaConsultaIniciada();

        this.showSpinner = false;               
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  carregaConsultaIniciada() {
    if ( this.turno == '' ) {
      this.erroMsg = 'Selecione o turno.';
      return;
    }

    this.consultaService.getConsultaIniciada( 
          this.clinicaId, 
          this.turno, 
          this.historicoObservacoesPageSize ).subscribe({

      next: (resp) => {
        this.quantPacientesNaFila = resp.quantPacientesNaFila;

        this.erroMsg = null;

        if ( resp.consultaIniciada == true ) {
          this.historicoObservacoes = resp.historicoObservacoes;

          if ( resp.consulta.id !== this.consulta.id ) {
            this.consulta = resp.consulta;
            this.pacienteAnexos = resp.pacienteAnexos;
            this.observacoesSave.observacoes = resp.consulta.observacoes;
          }

          this.infoMsg = null;
        } else {
          this.infoMsg = "Nenhuma consulta iniciada por enquanto."
        }
                
        setTimeout( () => {
          this.carregaConsultaIniciada();
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

    this.consultaService.alteraObservacoes( this.consulta.id, this.observacoesSave ).subscribe({
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

    if ( this.consulta.pacienteAnamneseCriada == false ) {
      this.infoMsg = "A anamnese do paciente não foi criada ainda.";
      return;
    }

    this.showSpinner = true;

    let pacienteId = this.consulta.pacienteId;

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
