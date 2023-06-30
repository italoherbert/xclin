import { Component } from '@angular/core';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalSave } from 'src/app/core/bean/profissional/profissional-save';
import { ContaProfissionalService } from 'src/app/core/service/conta-profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-profissional-conta-alterar',
  templateUrl: './profissional-conta-alterar.component.html',
  styleUrls: ['./profissional-conta-alterar.component.css']
})
export class ProfissionalContaAlterarComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  profissionalSave : ProfissionalSave = {
    nome: '',
    funcao: '',
    usuario: {
      username: '',
      senha: '',
      perfil: '',
      ignorarSenha: true
    }

  }

  funcoes : any[] = [];

  constructor(
    private contaProfissionalService: ContaProfissionalService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.loadEditTela().subscribe({
      next: (resp) => {
        this.profissionalSave = resp.contaProfissional;
        this.profissionalSave.usuario.ignorarSenha = true;

        this.funcoes = resp.funcoes;

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
        
    this.contaProfissionalService.altera( this.profissionalSave ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Profissional alterado com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

}
