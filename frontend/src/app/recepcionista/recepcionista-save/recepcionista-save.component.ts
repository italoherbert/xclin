import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { RecepcionistaSave } from 'src/app/bean/recepcionista/recepcionista-save';
import { RecepcionistaService } from 'src/app/service/recepcionista.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-recepcionista-save',
  templateUrl: './recepcionista-save.component.html',
  styleUrls: ['./recepcionista-save.component.css']
})
export class RecepcionistaSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  recepcionistaSave : RecepcionistaSave = {
    nome: '',
    clinicaId : 0,
    usuario: {
      username: '',
      senha: '',
      perfil: ''
    }
  }

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  senhaRepetida : any = '';

  constructor(
    private actRoute : ActivatedRoute, 
    private recepcionistaService: RecepcionistaService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    if ( id === '-1' ) {
      this.recepcionistaService.getRecepcionistaReg().subscribe( {
        next: ( resp ) => {
          this.clinicasIDs = resp.clinicasIDs;
          this.clinicasNomes = resp.clinicasNomes;

          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      } );
    } else {
      this.recepcionistaService.getRecepcionistaEdit( id ).subscribe({
        next: ( resp ) => {
          this.recepcionistaSave = resp.recepcionista;
          this.clinicasIDs = resp.clinicasIDs;
          this.clinicasNomes = resp.clinicasNomes;

          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    if ( this.recepcionistaSave.usuario.senha !== this.senhaRepetida ) {
      this.erroMsg = "As senhas informadas não correspondem.";
      return;
    }

    this.showSpinner = true;
    
    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    
    if ( id === '-1' ) { 
      this.recepcionistaService.registraRecepcionista( this.recepcionistaSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Recepcionista registrado com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.recepcionistaService.alteraRecepcionista( id, this.recepcionistaSave ).subscribe({
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

}
