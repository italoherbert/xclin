import { Component, Input, Output, EventEmitter } from '@angular/core';
import { PacienteService } from 'src/app/core/service/paciente.service';

@Component({
  selector: 'app-paciente-autocomplete-input',
  templateUrl: './paciente-autocomplete-input.component.html',
  styleUrls: ['./paciente-autocomplete-input.component.css']
})
export class PacienteAutocompleteInputComponent {

  @Input() clinicaId : number = 0;
  @Output() onSelect: EventEmitter<any> = new EventEmitter<any>();
  @Output() onErroCreate : EventEmitter<any> = new EventEmitter<any>();

  buscandoPacientes : boolean = false;
  buscarPacientes : boolean = false;

  pacienteId : number = 0;
  pacienteNome : string = '';

  pacientesIDs : number[] = [];
  pacientesNomes : string[] = [];

  constructor(
    private pacienteService : PacienteService
  ) {}

  onPacienteInput( event : any ) {
    if ( this.pacienteNome.length == 0 ) {
      this.pacientesIDs.splice( 0, this.pacientesIDs.length );
      this.pacientesNomes.splice( 0, this.pacientesNomes.length );
      return;    
    }

    if ( this.buscandoPacientes === true ) {
      this.buscarPacientes = true;
      return;
    }

    this.buscandoPacientes = true;

    this.pacienteService.listaPorNomePorClinica( this.clinicaId, this.pacienteNome, 4 ).subscribe( {
      next: (resp) => {
        this.pacientesIDs = resp.ids;
        this.pacientesNomes = resp.nomes;

        this.buscandoPacientes = false;

        if ( this.buscarPacientes === true ) {
          this.buscarPacientes = false;
          this.onPacienteInput( event );
        }
      },
      error: (erro) => {
        this.buscandoPacientes = false;
        this.buscarPacientes = false;

        this.onErroCreate.emit( erro );
      }
    } );    
  }

  onPacienteSelected( event : any ) {
    this.pacienteId = this.pacientesIDs[ event.option.id ];
    this.onSelect.emit( this.pacienteId );
  }
  
}
