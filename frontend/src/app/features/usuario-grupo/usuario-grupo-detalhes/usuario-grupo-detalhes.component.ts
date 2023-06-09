import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { UsuarioGrupoDetalhes } from 'src/app/core/bean/usuario-grupo/usuario-grupo-detalhes';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { UsuarioGrupoService } from 'src/app/core/service/usuario-grupo.service';

@Component({
  selector: 'app-usuario-grupo-detalhes',
  templateUrl: './usuario-grupo-detalhes.component.html',
  styleUrls: ['./usuario-grupo-detalhes.component.css']
})
export class UsuarioGrupoDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  grupoDetalhes : UsuarioGrupoDetalhes = {
    id : '',
    grupo : {
      id : '',
      nome : ''
    },
    acessos : []
  }

  constructor( private actRoute : ActivatedRoute, private usuarioGrupoService: UsuarioGrupoService, private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.usuarioGrupoService.getGrupoDetalhes( id ).subscribe({
      next: ( resp ) => {
        this.grupoDetalhes = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
