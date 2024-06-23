import { Component, Input } from '@angular/core';
import { AtendimentoFilaCompletaFiltro } from 'src/app/core/bean/atendimento/atendimento-fila-completa-filtro';
import { AtendimentoService } from 'src/app/core/service/atendimento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

import { Atendimento } from 'src/app/core/bean/atendimento/atendimento';
import { faCircleInfo } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-atendimento-fila-completa',
  templateUrl: './atendimento-fila-completa.component.html',
  styleUrls: ['./atendimento-fila-completa.component.css']
})
export class AtendimentoFilaCompletaComponent {
  
  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo
  }

  atendimentoFilaFiltro : AtendimentoFilaCompletaFiltro = {
    data : '',
    turno: ''
  }

  atendimentosColumns: string[] = ['nome', 'novoStatus', 'dataAtendimento', 'turno', 'detalhes' ];
  atendimentosDataSource = new MatTableDataSource<Atendimento>([]);

  constructor( 
    private actRoute: ActivatedRoute,
    private atendimentoService: AtendimentoService,
    private sistemaService: SistemaService
  ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let clinicaId = this.actRoute.snapshot.paramMap.get( 'clinicaId' );
    let profissionalId = this.actRoute.snapshot.paramMap.get( 'profissionalId' );
    let ano = this.actRoute.snapshot.paramMap.get( 'ano' );
    let mes = this.actRoute.snapshot.paramMap.get( 'mes' );
    let dia = this.actRoute.snapshot.paramMap.get( 'dia' );
    let turno = this.actRoute.snapshot.paramMap.get( 'turno' );

    this.atendimentoFilaFiltro.data = ano+'-'+mes+'-'+dia;
    this.atendimentoFilaFiltro.turno = ''+turno;

    this.atendimentoService.listaFilaCompleta( clinicaId, profissionalId, this.atendimentoFilaFiltro ).subscribe( {
      next: (resp) => {
        this.atendimentosDataSource.data = resp;

        if ( this.atendimentosDataSource.data.length == 0 )
          this.infoMsg = "Nenhuma atendimento agendada para esta data e turno.";
          
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );    
  }

}
