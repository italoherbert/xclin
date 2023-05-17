import { Component, Output, EventEmitter } from '@angular/core';
import { faAnglesLeft, faAnglesRight, faBarsProgress } from '@fortawesome/free-solid-svg-icons';
import { ConsultaService } from 'src/app/service/consulta.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-consulta-profissional-select',
  templateUrl: './consulta-profissional-select.component.html',
  styleUrls: ['./consulta-profissional-select.component.css']
})
export class ConsultaProfissionalSelectComponent {

  @Output() onClinicaSelecionada : EventEmitter<any> = new EventEmitter();
  @Output() onProfissionalSelecionado : EventEmitter<any> = new EventEmitter();  

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faAnglesLeft : faAnglesLeft,
    faAnglesRight : faAnglesRight,
    faBarsProgress : faBarsProgress
  }

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  profissionaisIDs : number[] = [];
  profissionaisNomes : string[] = [];

  clinicaId : number = 0;
  profissionalId : number = 0;
  
  constructor( 
    private consultaService : ConsultaService,
    private profissionalService : ProfissionalService, 
    private sistemaService : SistemaService ) {}


  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.consultaService.getNovaConsultaProfissionalSelect().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  clinicaSelecionada( event : any ) {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.profissionalService.listaPorClinica( this.clinicaId ).subscribe( {
      next: (resp) => {
        this.profissionaisIDs = resp.ids;
        this.profissionaisNomes = resp.nomes;

        this.onClinicaSelecionada.emit( { clinicaId : this.clinicaId } );

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } ); 
  }

  profissionalSelecionado( event : any ) {
    this.onProfissionalSelecionado.emit( { 
      clinicaId : this.clinicaId, profissionalId : this.profissionalId 
    } );
  }

}
