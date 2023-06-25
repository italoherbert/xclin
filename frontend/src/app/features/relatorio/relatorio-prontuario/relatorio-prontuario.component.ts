import { Component } from '@angular/core';
import { faFilePdf } from '@fortawesome/free-solid-svg-icons';
import { PacienteService } from 'src/app/core/service/paciente.service';
import { RelatorioService } from 'src/app/core/service/relatorio.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-relatorio-prontuario',
  templateUrl: './relatorio-prontuario.component.html',
  styleUrls: ['./relatorio-prontuario.component.css']
})
export class RelatorioProntuarioComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  buscandoPacientes : boolean = false;
  buscarPacientes : boolean = false;

  icons : any = {
    faFilePdf : faFilePdf
  }

  clinicaId : number = 0;
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  pacienteId : number = 0;

  constructor(
    private relatorioService : RelatorioService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.relatorioService.loadProntuarioTela().subscribe({
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
  }

  geraRelatorioProntuario() {
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.relatorioService.getRelatorioProntuario( this.pacienteId ).subscribe({
      next: (resp) => {
        this.sistemaService.criaDownloadAncora( resp, 'prontuario.pdf' );
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = "Falha na geração do relatório.";
        this.showSpinner = false;
      } 
    });
  }

  pacienteOnSelect( pacienteId : number ) {
    this.pacienteId = pacienteId;
  }

  pacienteOnErroCreate( erro : any ) {
    this.erroMsg = this.sistemaService.mensagemErro( erro );
  }

}
