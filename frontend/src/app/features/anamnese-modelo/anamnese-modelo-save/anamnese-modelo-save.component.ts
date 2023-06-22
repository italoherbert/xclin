import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { AnamneseModeloSave } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo-save';
import { AnamneseModeloService } from 'src/app/core/service/anamnese-modelo.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-anamnese-modelo-save',
  templateUrl: './anamnese-modelo-save.component.html',
  styleUrls: ['./anamnese-modelo-save.component.css']
})
export class AnamneseModeloSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  anamneseModeloSave : AnamneseModeloSave = {
    nome: ''
  }

  constructor(
    private actRoute : ActivatedRoute,
    private anamneseModeloService : AnamneseModeloService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( id != '-1' ) {
      this.infoMsg = null;
      this.erroMsg = null;

      this.showSpinner = true;

      this.anamneseModeloService.get( id ).subscribe( {
        next: (resp) => {
          this.anamneseModeloSave = resp;
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

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    if ( id == '-1' ) {
      this.anamneseModeloService.registra( this.anamneseModeloSave ).subscribe({
        next: (resp) => {
          this.infoMsg = "Modelo registrado com sucesso.";
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.anamneseModeloService.altera( id, this.anamneseModeloSave ).subscribe({
        next: (resp) => {
          this.infoMsg = "Modelo salvo com sucesso.";
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

}
