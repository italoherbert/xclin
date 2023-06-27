import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { PacienteAnexoSave } from 'src/app/core/bean/paciente-anexo/paciente-anexo-save';
import { PacienteAnexoService } from 'src/app/core/service/paciente-anexo.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-paciente-anexos',
  templateUrl: './paciente-anexos.component.html',
  styleUrls: ['./paciente-anexos.component.css']
})
export class PacienteAnexosComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faTrashCan : faTrashCan
  }

  anexoSave : PacienteAnexoSave = {
    nome: '',
    arquivo: ''
  }

  pacienteNome : string = "";
  anexos : any[] = [];

  constructor(
    private actRoute: ActivatedRoute,
    private pacienteAnexoService : PacienteAnexoService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.carregaAnexos();
  }

  carregaAnexos() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'pacienteId' );

    this.pacienteAnexoService.loadTela( pacienteId ).subscribe({
      next: (resp) => {
        this.pacienteNome = resp.pacienteNome;
        this.anexos = resp.anexos;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  anexoOnChange( event : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let anexoFile = event.target.files[0];

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'pacienteId' );

    const reader = new FileReader();
    reader.readAsDataURL( anexoFile );
    reader.onload = () => {
      this.anexoSave.nome = anexoFile.name;
      this.anexoSave.arquivo = reader.result;

      this.pacienteAnexoService.registra( pacienteId, this.anexoSave ).subscribe({
        next: (resp) => {
          this.carregaAnexos();
          
          this.infoMsg = "Anexo registrado com sucesso.";
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
    reader.onerror = ( erro ) => {
      this.erroMsg = "Erro no carregamento do arquivo antes do envio.";
      this.showSpinner = false;
    }
    
  }

  downloadAnexo( anexoId : any ) {
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

  removeAnexo( anexoId : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.pacienteAnexoService.deleta( anexoId ).subscribe({
      next: (resp) => {
        this.carregaAnexos();
        this.infoMsg = "Anexo deletado com sucesso.";
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }
  
}
