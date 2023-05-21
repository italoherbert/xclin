import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { RecepcionistaSave } from 'src/app/bean/recepcionista/recepcionista-save';
import { RecepcionistaService } from 'src/app/service/recepcionista.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-recepcionista-conta-alterar',
  templateUrl: './recepcionista-conta-alterar.component.html',
  styleUrls: ['./recepcionista-conta-alterar.component.css']
})
export class RecepcionistaContaAlterarComponent {
 
  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  recepcionistaSave : RecepcionistaSave = {
    nome: '',
    usuario: {
      username: '',
      senha: '',
      perfil: '',
      ignorarSenha: true
    }

  }

  senhaRepetida : any = '';
  clinicaId : number = 0;

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor(
    private recepcionistaService: RecepcionistaService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.recepcionistaService.getRecepcionistaEditPorLogadoUID().subscribe({
      next: (resp) => {
        this.recepcionistaSave = resp.recepcionista;
        this.recepcionistaSave.usuario.ignorarSenha = true;

        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;
        this.clinicaId = resp.recepcionista.clinicaId;

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
        
    this.recepcionistaService.alteraRecepcionistaPorLogadoUID( this.clinicaId, this.recepcionistaSave ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Recepcionista alterado com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

}
