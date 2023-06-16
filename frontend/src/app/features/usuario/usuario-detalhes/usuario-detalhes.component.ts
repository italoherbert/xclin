import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faEye, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Usuario } from 'src/app/core/bean/usuario/usuario';
import { SistemaService } from 'src/app/core/service/sistema.service';
import { UsuarioService } from 'src/app/core/service/usuario.service';

@Component({
  selector: 'app-usuario-detalhes',
  templateUrl: './usuario-detalhes.component.html',
  styleUrls: ['./usuario-detalhes.component.css']
})
export class UsuarioDetalhesComponent implements OnInit {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft,
    faEye : faEye
  }

  usuario : Usuario = {
    id : '',
    username : '',
    perfil: '',
    perfilLabel: ''
  }

  constructor( private actRoute : ActivatedRoute, private usuarioService: UsuarioService, private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.usuarioService.getUsuario( id ).subscribe({
      next: ( resp ) => {
        this.usuario = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }
  
}
