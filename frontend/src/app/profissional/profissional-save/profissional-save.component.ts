import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalSave } from 'src/app/bean/profissional/profissional-save';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-profissional-save',
  templateUrl: './profissional-save.component.html',
  styleUrls: ['./profissional-save.component.css']
})
export class ProfissionalSaveComponent {

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
    valorConsulta: 0,
    usuario: {
      username: '',
      senha: '',
      perfil: ''
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

    if ( id == '-1' ) {        
      this.profissionalService.getProfissionalReg().subscribe( {
        next: (resp) => {
          this.funcoes = resp.funcoes;
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    } else {
      this.profissionalService.getProfissionalEdit( id ).subscribe( {
        next: (resp) => {
          this.profissionalSave = resp.profissional;
          this.funcoes = resp.funcoes;

          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    }    
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    if ( this.profissionalSave.usuario.senha !== this.senhaRepetida ) {
      this.erroMsg = "As senhas informadas não correspondem.";
      return;
    }

    this.showSpinner = true;
    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    
    if ( id === '-1' ) { 
      this.profissionalService.registraProfissional( this.profissionalSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Profissional registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.profissionalService.alteraParcialProfissional( id, this.profissionalSave ).subscribe({
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

}
