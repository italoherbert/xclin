import { Component } from '@angular/core';
import { faFilePdf } from '@fortawesome/free-solid-svg-icons';
import { BalancoDoDia } from 'src/app/core/bean/relatorio/balanco-do-dia';
import { RelatorioService } from 'src/app/core/service/relatorio.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { UsuarioService } from 'src/app/core/service/usuario.service';

import * as moment from 'moment';

@Component({
  selector: 'app-relatorio-balanco-dia',
  templateUrl: './relatorio-balanco-dia.component.html',
  styleUrls: ['./relatorio-balanco-dia.component.css']
})
export class RelatorioBalancoDiaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faFilePdf : faFilePdf
  }

  balancoDoDia : BalancoDoDia = {
    usuarioId : 0,
    incluirTodosOsUsuarios : false,
    dataDia : ''
  }

  clinicaId : number = 0;
  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  usuarioId : number = 0;
  usuariosIDs : number[] = [];
  usuariosNomes : string[] = [];
  
  constructor(
    private relatorioService : RelatorioService,
    private usuarioService : UsuarioService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.balancoDoDia.dataDia = moment().format();

    this.relatorioService.getBalancoDoDiaLoad().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;
        
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      } 
    });
  }

  clinicaSelecionada() {
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.usuarioService.listaPorClinica( this.clinicaId ).subscribe({
      next: (resp) => {
        this.usuariosIDs = resp.ids;
        this.usuariosNomes = resp.nomes;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      } 
    });
  }

  geraRelatorioBalancoDoDia() {
    this.erroMsg = null;
    this.infoMsg = null;

    this.showSpinner = true;

    this.relatorioService.getRelatorioBalancoDoDia( this.clinicaId, this.balancoDoDia ).subscribe({
      next: (resp) => {
        this.sistemaService.criaDownloadAncora( resp, 'relatorio-do-dia.pdf' );
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = "Falha na geração do relatório.";
        this.showSpinner = false;
      } 
    });
  }

}
