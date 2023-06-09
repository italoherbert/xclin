import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faRotate, faSave } from '@fortawesome/free-solid-svg-icons';
import { ClinicaSave } from 'src/app/core/bean/clinica/clinica-save';
import { ClinicaService } from 'src/app/core/service/clinica.service';
import { LocalidadeService } from 'src/app/core/service/localidade.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-clinica-save',
  templateUrl: './clinica-save.component.html',
  styleUrls: ['./clinica-save.component.css']
})
export class ClinicaSaveComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faRotate : faRotate,
    faCircleLeft : faCircleLeft
  }

  clinicaSave : ClinicaSave = {
    nome: '',
    telefone: '',
    email: '',
    endereco : {
      logradouro : '',
      numero: '',
      bairro: '',
      codigoMunicipio: 0,
      codigoUf: 0
    }
  }

  ufs : any[] = [];
  municipios : any[] = [];

  constructor( 
    private actRoute : ActivatedRoute, 
    private clinicaService: ClinicaService,
    private localidadeService: LocalidadeService, 
    private sistemaService: SistemaService ) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    if ( id == '-1' ) {
      this.clinicaService.getRegClinica().subscribe({
        next: (resp) => {
          this.ufs = resp.ufs;
          this.showSpinner = false;
        }, 
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {      
      this.clinicaService.getEditClinica( id ).subscribe( {
        next: ( resp ) => {
          this.ufs = resp.ufs;
          this.municipios = resp.municipios;
          this.clinicaSave = resp.clinica;

          this.showSpinner = false;
        },
        error: ( erro ) => {
          console.log( erro );
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
    
    if ( id === '-1' ) { 
      this.clinicaService.registraClinica( this.clinicaSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Clinica registrada com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.clinicaService.alteraClinica( id, this.clinicaSave ).subscribe({
        next: ( resp ) => {
          this.infoMsg = "Clinica alterada com sucesso.";
          this.showSpinner = false;
        },
        error: ( erro ) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

  ufSelectionChange( ufid : number ) {
    this.showSpinner = true;
    this.localidadeService.getMunicipios( ufid ).subscribe( {
      next: (resp) => {
        this.municipios = resp;
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

}
