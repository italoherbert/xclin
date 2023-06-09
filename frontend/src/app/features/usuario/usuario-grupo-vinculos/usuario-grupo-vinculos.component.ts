import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { UsuarioGrupoVinculosSave } from 'src/app/core/bean/usuario/usuario-grupo-vinculos';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { UsuarioService } from 'src/app/core/service/usuario.service';

@Component({
  selector: 'app-usuario-grupo-vinculos',
  templateUrl: './usuario-grupo-vinculos.component.html',
  styleUrls: ['./usuario-grupo-vinculos.component.css']
})
export class UsuarioGrupoVinculosComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave
  }

  vinculosObj : any = {
    usuario : {
      username : ''
    }
  };

  vinculosSave : UsuarioGrupoVinculosSave = {
    grupos : []
  }

  constructor( private actRoute: ActivatedRoute, private usuarioService : UsuarioService, private sistemaService : SistemaService ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.usuarioService.getUsuarioGrupoVinculados( id ).subscribe( {
      next: ( resp ) => {
        this.vinculosObj = resp;        
        this.showSpinner = false;
      }, 
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }      
    } );
  }

  grupoSelecionado( e : any, i : any ) {
    this.vinculosObj.vinculadosFlags[ i ] = e.selected;
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.vinculosSave.grupos.splice( 0, this.vinculosSave.grupos.length );

    let len = this.vinculosObj.vinculadosFlags.length;
    for( let i = 0; i < len; i++ ) {
      let flag = this.vinculosObj.vinculadosFlags[ i ];
      if ( flag === true ) {
        this.vinculosSave.grupos.push( this.vinculosObj.grupos[ i ].id );            
      }
    }

    this.usuarioService.salvaUsuarioGrupoVinculados( id, this.vinculosSave ).subscribe( {
      next: (resp) => {        
        this.infoMsg = "Vínculos salvos com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

}
