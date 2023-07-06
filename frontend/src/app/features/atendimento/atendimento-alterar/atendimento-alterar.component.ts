import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCircleLeft, faSave } from '@fortawesome/free-solid-svg-icons';
import { AtendimentoAlter } from 'src/app/core/bean/atendimento/atendimento-alter';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-atendimento-alterar',
  templateUrl: './atendimento-alterar.component.html',
  styleUrls: ['./atendimento-alterar.component.css']
})
export class AtendimentoAlterarComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  atendimentoAlter : AtendimentoAlter = {
    observacoes : '',
  }

  pacienteNome : string = '';
  clinicaNome : string = '';

  constructor(
    private router: Router,
    private actRoute: ActivatedRoute,
    private atendimentoService: AtendimentoService,
    private sistemaService: SistemaService) {}

  ngOnInit() {    
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;
    
    let id = this.actRoute.snapshot.paramMap.get( 'atendimentoId' );
    
    this.atendimentoService.getAtendimentoAlter( id ).subscribe({
      next: (resp) => {
        this.atendimentoAlter.observacoes = resp.atendimento.observacoes;
        
        this.pacienteNome = resp.atendimento.pacienteNome;
        this.clinicaNome = resp.atendimento.clinicaNome;

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

    let id = this.actRoute.snapshot.paramMap.get( 'atendimentoId' );

    this.atendimentoService.alteraAtendimento( id, this.atendimentoAlter ).subscribe({
      next: ( resp ) => {
        this.infoMsg = "Atendimento alterada com sucesso.";
        this.showSpinner = false;
      },
      error: ( erro ) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });    
  }

  paraDetalhes() {
    let id = this.actRoute.snapshot.paramMap.get( 'atendimentoId' );
    this.router.navigate([ '/app', { outlets : { page : 'atendimento-detalhes/'+id }}]);
  }

}
