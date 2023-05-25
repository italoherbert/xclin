import { Component } from '@angular/core';
import { faCirclePause, faCirclePlay } from '@fortawesome/free-solid-svg-icons';
import { Consulta } from 'src/app/bean/consulta/consulta';
import { ConsultaService } from 'src/app/service/consulta.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-consulta-atendimento',
  templateUrl: './consulta-atendimento.component.html',
  styleUrls: ['./consulta-atendimento.component.css']
})
export class ConsultaAtendimentoComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCirclePlay : faCirclePlay,
    faCirclePause : faCirclePause
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
    clinicaId: 0,
    clinicaNome: '',
    especialidadeId: 0,
    especialidadeNome: '',
    statusLabel: '',
    turnoLabel: ''
  }

  clinicaId : number = 0;
  turno: string = '';

  turnos : any[] = [];

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  carregarAtivado : boolean = false;

  constructor(
    private consultaService : ConsultaService,
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
        
        this.showSpinner = false;               
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  iniciaCarregamento() {
    if ( !this.carregarAtivado ) {
      this.carregarAtivado = true;
      this.carregaConsultaIniciada();
    }
  }

  paraCarregamento() {
    this.carregarAtivado = false;
  }

  carregaConsultaIniciada() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    if ( this.turno == '' ) {
      this.erroMsg = 'Selecione o turno.';
      return;
    }

    this.consultaService.getConsultaIniciada( this.clinicaId, this.turno ).subscribe({
      next: (resp) => {
        if ( resp.consultaIniciada == true ) {
          this.consulta = resp.consulta;
        } else {
          this.infoMsg = "Nenhuma consulta iniciada por enquanto."
        }
        
        this.showSpinner = false;   
        
        if ( this.carregarAtivado === true ) {
          setTimeout( () => {
            this.carregaConsultaIniciada();
          }, 5000 );
        }
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
