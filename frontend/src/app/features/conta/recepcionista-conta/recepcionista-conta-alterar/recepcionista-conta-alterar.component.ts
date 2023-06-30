import { Component } from '@angular/core';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { RecepcionistaSave } from 'src/app/core/bean/recepcionista/recepcionista-save';
import { ContaRecepcionistaService } from 'src/app/core/service/conta-recepcionista.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

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

  constructor(
    private contaRecepcionistaService: ContaRecepcionistaService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.contaRecepcionistaService.loadEdit().subscribe({
      next: (resp) => {
        this.recepcionistaSave = resp.recepcionista;
        this.recepcionistaSave.usuario.ignorarSenha = true;

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
        
    this.contaRecepcionistaService.altera( this.clinicaId, this.recepcionistaSave ).subscribe({
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
