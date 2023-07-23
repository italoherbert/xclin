import { Component } from '@angular/core';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { UsuarioSave } from 'src/app/core/bean/usuario/usuario-save';
import { ContaSuporteService } from 'src/app/core/service/conta-suporte.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-suporte-conta-alterar',
  templateUrl: './suporte-conta-alterar.component.html',
  styleUrls: ['./suporte-conta-alterar.component.css']
})
export class SuporteContaAlterarComponent {
  
  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  usuarioSave : UsuarioSave = {   
      username: '',
      senha: '',
      perfil: '',
      ignorarSenha: true    
  }

  constructor(
    private contaSuporteService: ContaSuporteService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.contaSuporteService.loadEdit().subscribe({
      next: (resp) => {
        this.usuarioSave = resp.usuario;
        this.usuarioSave.ignorarSenha = true;

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
        
    this.contaSuporteService.altera( this.usuarioSave ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Suporte alterado com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

}
