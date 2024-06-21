import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faSave, faX } from '@fortawesome/free-solid-svg-icons';
import { ClinicaLogoSave } from 'src/app/core/bean/clinica/clinica-logo-save';
import { ClinicaService } from 'src/app/core/service/clinica.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-clinica-logo',
  templateUrl: './clinica-logo.component.html',
  styleUrls: ['./clinica-logo.component.css']
})
export class ClinicaLogoComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faX : faX,
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  logoNome : any = null;
  logo : any = null;

  logoSave : ClinicaLogoSave = {
    logo: ''
  }

  constructor( 
    private actRoute : ActivatedRoute,
    private clinicaService : ClinicaService,
    private sistemaService : SistemaService 
  ) {}

  ngOnInit() {
    this.carregaLogo();
  }

  carregaLogo() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.clinicaService.getLogo( id ).subscribe( {
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

  salvaLogo( event : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    const file : File = event.target.files[0];
    if ( file ) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.logoSave.logo = ''+reader.result;
        this.logoNome = file.name;
        this.clinicaService.salvaLogo( id, this.logoSave ).subscribe( {
          next: ( dados ) => {
            this.carregaLogo();
            this.showSpinner = false;
          },
          error: ( erro ) => {
            this.erroMsg = this.sistemaService.mensagemErro( erro );
            this.showSpinner = false;
          }
        } );
      };
    }
  }

  paraLogoDefault() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );
    this.clinicaService.paraLogoDefault( id ).subscribe( {
      next: ( dados ) => {
        this.carregaLogo();
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );    
  }

}
