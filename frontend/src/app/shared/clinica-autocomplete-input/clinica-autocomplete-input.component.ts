import { Component, Output, EventEmitter } from '@angular/core';
import { ClinicaService } from 'src/app/core/service/clinica.service';

@Component({
  selector: 'app-clinica-autocomplete-input',
  templateUrl: './clinica-autocomplete-input.component.html',
  styleUrls: ['./clinica-autocomplete-input.component.css']
})
export class ClinicaAutocompleteInputComponent {

  @Output() onSelect: EventEmitter<any> = new EventEmitter<any>();
  @Output() onErroCreate : EventEmitter<any> = new EventEmitter<any>();

  buscandoClinicas : boolean = false;
  buscarClinicas : boolean = false;

  clinicaId : number = 0;
  clinicaNome : string = '';

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  constructor(
    private clinicaService : ClinicaService
  ) {}

  onClinicaInput( event : any ) {
    if ( this.clinicaNome.length == 0 ) {
      this.clinicasIDs.splice( 0, this.clinicasIDs.length );
      this.clinicasNomes.splice( 0, this.clinicasNomes.length );
      return;    
    }

    if ( this.buscandoClinicas === true ) {
      this.buscarClinicas = true;
      return;
    }

    this.buscandoClinicas = true;

    this.clinicaService.listaPorNome( this.clinicaNome, 4 ).subscribe( {
      next: (resp) => {
        this.clinicasIDs = resp.ids;
        this.clinicasNomes = resp.nomes;

        this.buscandoClinicas = false;

        if ( this.buscarClinicas === true ) {
          this.buscarClinicas = false;
          this.onClinicaInput( event );
        }
      },
      error: (erro) => {
        this.buscandoClinicas = false;
        this.buscarClinicas = false;

        this.onErroCreate.emit( erro );
      }
    } );    
  }

  onClinicaSelected( event : any ) {
    this.clinicaId = this.clinicasIDs[ event.option.id ];
    this.onSelect.emit( this.clinicaId );
  }

  limpa() {
    this.clinicaId = 0;
    this.clinicaNome = '';
    
    this.clinicasIDs.splice( 0, this.clinicasIDs.length );
    this.clinicasNomes.splice( 0, this.clinicasNomes.length );
  }

}