import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { ConsultaAlter } from 'src/app/core/bean/consulta/consulta-alter';
import { ConsultaService } from 'src/app/core/service/consulta.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-consulta-alterar',
  templateUrl: './consulta-alterar.component.html',
  styleUrls: ['./consulta-alterar.component.css']
})
export class ConsultaAlterarComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  consultaAlter : ConsultaAlter = {
    status : '',
    valor : 0,
    retorno : false,
    observacoes : '',
  }

  turnos : any[] = [];
  statuses : any[] = [];

  pacienteNome : string = '';
  clinicaNome : string = '';

  senhaRepetida : any = '';

  constructor(
    private router: Router,
    private actRoute: ActivatedRoute,
    private consultaService: ConsultaService,
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    let id = this.actRoute.snapshot.paramMap.get( 'consultaId' );
    
    this.consultaService.getConsultaAlter( id ).subscribe({
      next: (resp) => {
        this.consultaAlter = resp.consulta;
        this.turnos = resp.turnos;
        this.statuses = resp.statuses;

        this.pacienteNome = resp.consulta.pacienteNome;
        this.clinicaNome = resp.consulta.clinicaNome;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });   
  }

  altera() {
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'consultaId' );

    this.consultaService.alteraConsulta( id, this.consultaAlter ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Consulta alterada com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

  paraDetalhes() {
    let id = this.actRoute.snapshot.paramMap.get( 'consultaId' );
    this.router.navigate([ '/app', { outlets : { page : 'consulta-detalhes/'+id }}]);
  }

}
