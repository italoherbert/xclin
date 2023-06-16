import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faEye, faLink, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { ClinicaService } from 'src/app/core/service/clinica.service';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { UsuarioService } from 'src/app/core/service/usuario.service';

@Component({
  selector: 'app-usuario-clinica-vinculos',
  templateUrl: './usuario-clinica-vinculos.component.html',
  styleUrls: ['./usuario-clinica-vinculos.component.css']
})
export class UsuarioClinicaVinculosComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faTrashCan : faTrashCan,
    faLink : faLink
  }

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  clinicaId : number = 0;
  clinicaNome : string = '';

  vinculos : any[] = [];

  buscandoClinicas : boolean = false;
  buscarClinicas : boolean = false;

  constructor(
    private actRoute : ActivatedRoute,    
    private usuarioService : UsuarioService,
    private clinicaService : ClinicaService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.carregaVinculos();
  }

  carregaVinculos() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let usuarioId = this.actRoute.snapshot.paramMap.get( 'id' );

    this.usuarioService.getClinicasVinculos( usuarioId ).subscribe( {
      next: ( resp ) => {
        this.vinculos = resp;
        this.showSpinner = false;        
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  remove( id : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.usuarioService.deletaClinicaVinculo( id ).subscribe( {
      next: (resp) => {
        this.infoMsg = "Vínculo deletado com sucesso.";
        this.showSpinner = true;

        this.carregaVinculos();
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  onClinicaInput( event : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    if ( this.clinicaNome.length == 0 ) {
      this.clinicasIDs.splice( 0, this.clinicasIDs.length );
      this.clinicasNomes.splice( 0, this.clinicasNomes.length );
      return;
    }

    if ( this.buscandoClinicas === true ) {
      this.buscarClinicas = true;
      return;    
    }

    this.buscandoClinicas = true;

    this.clinicaService.listaPorNome( this.clinicaNome, 5 ).subscribe( {
      next: (resp) => {
        this.clinicasIDs = resp.ids;
        this.clinicasNomes = resp.nomes;

        this.buscandoClinicas = false;

        if ( this.buscarClinicas === true ) {
          this.buscarClinicas = false;
          this.onClinicaInput( event );     
        }   

      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.buscarClinicas = false;
      } 
    } );
  }

  onClinicaSelected( event : any ) {
    this.clinicaId = this.clinicasIDs[ event.option.id ];
  }

  vinculaClinica() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let usuarioId = this.actRoute.snapshot.paramMap.get( 'id' );

    this.usuarioService.criaClinicaVinculo( usuarioId, this.clinicaId ).subscribe( {
      next: (resp) => {
        this.clinicaId = 0;
        this.clinicaNome = '';

        this.carregaVinculos();
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  removeVinculo( id : any ) {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    this.usuarioService.deletaClinicaVinculo( id ).subscribe( {
      next: (resp) => {
        this.carregaVinculos();
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

}
