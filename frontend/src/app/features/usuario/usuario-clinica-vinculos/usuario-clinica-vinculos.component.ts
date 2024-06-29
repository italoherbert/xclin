import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { faEye, faLink, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Usuario } from 'src/app/core/bean/usuario/usuario';
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

  usuario : Usuario = {
    id: 0,
    username: '',
    perfil: '',
    perfilLabel: ''
  }

  clinicaId : number = 0;

  vinculosColumns : string[] = [ 'nome', 'remover' ];
  vinculosDataSource = new MatTableDataSource<any>([]);

  constructor(
    private actRoute : ActivatedRoute,    
    private usuarioService : UsuarioService,
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
        this.usuario = resp.usuario;
        this.vinculosDataSource.data = resp.vinculos;
        
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

  onClinicaSelected( cid : any ) {
    this.clinicaId = cid;
  }

  onClinicaSelectErroCreate( erro : any ) {
    this.erroMsg = this.sistemaService.mensagemErro( erro );
  }

  vinculaClinica() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let usuarioId = this.actRoute.snapshot.paramMap.get( 'id' );

    this.usuarioService.criaClinicaVinculo( usuarioId, this.clinicaId ).subscribe( {
      next: (resp) => {
        this.clinicaId = 0;        

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
