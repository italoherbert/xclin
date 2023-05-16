import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConsultaService } from 'src/app/service/consulta.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-agenda-remarcar',
  templateUrl: './agenda-remarcar.component.html',
  styleUrls: ['./agenda-remarcar.component.css']
})
export class AgendaRemarcarComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {

  }

  quantidadesAgrupadasPorDia : any[][] = [];

  constructor(
    private router: Router,
    private actRoute: ActivatedRoute,
    private consultaService: ConsultaService,
    private sistemaService: SistemaService
  ) {}

  onCalendarioAlterado( event : any ) {
    let mes = event.mes;
    let ano = event.ano;

    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let id = this.actRoute.snapshot.paramMap.get( 'consultaId' );

    this.consultaService.getQuantidadesAgrupadasPorDiaDoMesCID( id, mes, ano ).subscribe( {
        next: (resp ) => {
          this.quantidadesAgrupadasPorDia = resp;
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
    } );

  }

  onDiaClicado( event : any ) {
    let dia = event.dia;
    let mes = event.mes;
    let ano = event.ano;

    let id = this.actRoute.snapshot.paramMap.get( 'consultaId' );

    this.router.navigate([ '/app', { outlets : { page : 'consulta-remarcar-save/'+id+'/'+dia+'/'+mes+'/'+ano } } ]);
  }

}
