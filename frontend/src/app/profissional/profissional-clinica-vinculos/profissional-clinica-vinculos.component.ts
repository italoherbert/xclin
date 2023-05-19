import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faFilter, faSave } from '@fortawesome/free-solid-svg-icons';
import { Profissional } from 'src/app/bean/profissional/profissional';
import { ClinicaService } from 'src/app/service/clinica.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-profissional-clinica-vinculos',
  templateUrl: './profissional-clinica-vinculos.component.html',
  styleUrls: ['./profissional-clinica-vinculos.component.css']
})
export class ProfissionalClinicaVinculosComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faFilter : faFilter
  }

  profissional : Profissional = {
    id : 0,
    nome : '',
    funcao: '',
    funcaoLabel: '',
    valorConsulta: 0,
    usuario : {
      id : 0,
      username : '',
      perfil : '',
      perfilLabel : ''
    }
  };

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = []; 

  filtroClinicasIDs : number[] = [];
  filtroClinicasNomes : string[] = [];

  filtroClinicaNomeIni : string = '';

  constructor( 
    private actRoute : ActivatedRoute, 
    private profissionalService: ProfissionalService,
    private clinicaService: ClinicaService, 
    private sistemaService : SistemaService ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.profissionalService.getClinicaVinculos( id ).subscribe( {
      next: (resp) => {
        this.profissional = resp.profissional;
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );    
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' ); 
  
    let vinculos = { clinicas : this.clinicasIDs };
    this.profissionalService.salvaProfissionalClinicas( id, vinculos ).subscribe( {
      next: (resp) => {
        this.infoMsg = "Vínculos salvos com sucesso.";
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  }

  filtraClinicas() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.filtroClinicasIDs.splice( 0, this.filtroClinicasIDs.length );
    this.filtroClinicasNomes.splice( 0, this.filtroClinicasNomes.length );

    this.clinicaService.filtraClinicasPorNome( this.filtroClinicaNomeIni ).subscribe( {
      next: (resp) => {
        let clinicas = resp;
        for( let i = 0; i < clinicas.length; i++ ) {
          let jaAdicionado = false;
          for( let j = 0; jaAdicionado === false && j < this.clinicasIDs.length; i++ )
            if ( clinicas[ i ].id === this.clinicasIDs[ j ] )
              jaAdicionado = true;

          if ( jaAdicionado == false ) {
            this.filtroClinicasIDs.push( clinicas[ i ].id );
            this.filtroClinicasNomes.push( clinicas[ i ].nome );
          }
        }

        if ( this.filtroClinicasIDs.length === 0 )
          this.infoMsg = "Nenhuma clínica não adicionada encontrada.";

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );
  } 

  chipsClinicaRemovida( e : any, i : any ) {   
    this.filtroClinicasIDs.push( this.clinicasIDs[ i ] );
    this.filtroClinicasNomes.push( this.clinicasNomes[ i ] );

    this.clinicasIDs.splice( i, 1 );
    this.clinicasNomes.splice( i, 1 );
  }

  chipsClinicaAdicionada( e : any, i : any ) {
    this.clinicasIDs.push( this.filtroClinicasIDs[ i ] );
    this.clinicasNomes.push( this.filtroClinicasNomes[ i ] );

    this.filtroClinicasIDs.splice( i, 1 );
    this.filtroClinicasNomes.splice( i, 1 );
  }

}
