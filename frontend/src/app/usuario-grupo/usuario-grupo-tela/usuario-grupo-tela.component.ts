import { Component } from '@angular/core';
import { faCircleInfo, faFilter, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { SistemaService } from 'src/app/service/sistema.service';

import { UsuarioGrupoFiltro } from 'src/app/bean/usuario-grupo/usuario-grupo-filtro';
import { UsuarioGrupoService } from 'src/app/service/usuario-grupo.service';

@Component({
  selector: 'app-usuario-grupo-tela',
  templateUrl: './usuario-grupo-tela.component.html',
  styleUrls: ['./usuario-grupo-tela.component.css']
})
export class UsuarioGrupoTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faFilter : faFilter,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  grupoFiltro : UsuarioGrupoFiltro = {
    nomeIni : ''
  }

  grupos : any;

  constructor( private usuarioGrupoService: UsuarioGrupoService, private sistemaService: SistemaService) {}

  filtra() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.usuarioGrupoService.filtraGrupos( this.grupoFiltro ).subscribe({
      next: ( resp ) => {
        this.grupos = resp;
        if ( this.grupos.length == 0 )
          this.infoMsg = "Nenhum grupo encontrado.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  remove( id : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.usuarioGrupoService.deletaGrupo( id ).subscribe({
      next: ( resp ) => {
        this.filtra();

        this.infoMsg = 'Usuario deletado com sucesso!';
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }


}
