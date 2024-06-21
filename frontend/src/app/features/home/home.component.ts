import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClinicaService } from 'src/app/core/service/clinica.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  username : string = '';
  perfilLabel : string = '';
  logo : string = '';

  constructor( 
    private clinicaService : ClinicaService,
    private sistemaService : SistemaService
  ) {}

  ngOnInit() {
    this.username = ''+localStorage.getItem( 'username' );
    this.perfilLabel = ''+localStorage.getItem( 'perfil-label' );

    this.infoMsg = null;
    this.erroMsg = null;
    this.showSpinner = true;

    this.clinicaService.getInicialPageLogo().subscribe( {
      next: ( dados ) => {
        this.logo = dados.imageBase64;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }      
    } );    
  }

}
