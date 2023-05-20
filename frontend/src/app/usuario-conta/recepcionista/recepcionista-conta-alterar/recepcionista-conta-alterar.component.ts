import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalSave } from 'src/app/bean/profissional/profissional-save';
import { ProfissionalService } from 'src/app/service/profissional.service';
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

  senhaRepetida : any = '';

  funcoes : any[] = [];

  constructor(
    private actRoute : ActivatedRoute, 
    private profissionalService: ProfissionalService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.profissionalService.getProfissionalEditPorLogadoUID().subscribe({
      next: (resp) => {
        this.profissionalSave = resp;
        this.profissionalSave.usuario.ignorarSenha = true;

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
    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    
    this.profissionalService.alteraProfissional( id, this.profissionalSave ).subscribe({
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
