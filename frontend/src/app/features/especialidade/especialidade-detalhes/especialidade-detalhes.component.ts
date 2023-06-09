import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCircleLeft, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Especialidade } from 'src/app/core/bean/especialidade/especialidade';
import { EspecialidadeService } from 'src/app/core/service/especialidade.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-especialidade-detalhes',
  templateUrl: './especialidade-detalhes.component.html',
  styleUrls: ['./especialidade-detalhes.component.css']
})
export class EspecialidadeDetalhesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare : faPenToSquare,
    faCircleLeft : faCircleLeft
  }

  especialidade : Especialidade = {
    id : 0,
    nome: ''
  }

  constructor( 
    private actRoute : ActivatedRoute, 
    private especialidadeService: EspecialidadeService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'id' );

    this.especialidadeService.getEspecialidade( id ).subscribe({
      next: ( resp ) => {
        this.especialidade = resp;
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}