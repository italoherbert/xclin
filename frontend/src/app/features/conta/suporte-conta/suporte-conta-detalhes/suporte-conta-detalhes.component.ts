import { Component } from '@angular/core';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Usuario } from 'src/app/core/bean/usuario/usuario';
import { ContaSuporteService } from 'src/app/core/service/conta-suporte.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-suporte-conta-detalhes',
  templateUrl: './suporte-conta-detalhes.component.html',
  styleUrls: ['./suporte-conta-detalhes.component.css']
})
export class SuporteContaDetalhesComponent {
  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  suporte : Usuario = {
    id: 0,
    username: '',
    perfil: '',
    perfilLabel: ''
  }

  constructor( 
    private suporteContaService: ContaSuporteService, 
    public sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.suporteContaService.get().subscribe({
      next: ( resp ) => {
        this.suporte = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
