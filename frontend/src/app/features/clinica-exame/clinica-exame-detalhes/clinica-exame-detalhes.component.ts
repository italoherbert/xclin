import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { ClinicaExame } from 'src/app/core/bean/clinica-exame/clinica-exame';
import { ClinicaExameService } from 'src/app/core/service/clinica-exame.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-clinica-exame-detalhes',
  templateUrl: './clinica-exame-detalhes.component.html',
  styleUrls: ['./clinica-exame-detalhes.component.css']
})
export class ClinicaExameDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  exame : ClinicaExame = {
    id : 0,
    nome: '', 
    descricao: '',
    valor : 0
  }

  constructor( 
    private actRoute : ActivatedRoute, 
    private clinicaExameService: ClinicaExameService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let exameId = this.actRoute.snapshot.paramMap.get( 'clinicaExameId' );

    this.clinicaExameService.get( exameId ).subscribe({
      next: ( resp ) => {
        this.exame = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }


}
