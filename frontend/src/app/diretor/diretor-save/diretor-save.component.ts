import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { DiretorSave } from 'src/app/bean/diretor/diretor-save';
import { DiretorService } from 'src/app/service/diretor.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-diretor-save',
  templateUrl: './diretor-save.component.html',
  styleUrls: ['./diretor-save.component.css']
})
export class DiretorSaveComponent {
  
  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  diretorSave : DiretorSave = {
    nome: '',
    usuario: {
      username: '',
      senha: '',
      perfil: ''
    }

  }

  senhaRepetida : any = '';

  perfis : any[] = [];

  constructor(
    private actRoute : ActivatedRoute, 
    private diretorService: DiretorService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( id !== '-1' ) {
      this.infoMsg = null;
      this.erroMsg = null;

      this.showSpinner = true;
      
      this.diretorService.getDiretor( id ).subscribe( {
        next: ( resp ) => {
          this.diretorSave = resp;
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    }
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    if ( this.diretorSave.usuario.senha !== this.senhaRepetida ) {
      this.erroMsg = "As senhas informadas não correspondem.";
      return;
    }

    this.showSpinner = true;
    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    
    if ( id === '-1' ) { 
      this.diretorService.registraDiretor( this.diretorSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Diretor registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.diretorService.alteraDiretor( id, this.diretorSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Diretor alterado com sucesso.";
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

