import { Component, Input } from '@angular/core';
import { ConsultaFilaCompletaFiltro } from 'src/app/core/bean/consulta/consulta-fila-completa-filtro';
import { ConsultaService } from 'src/app/core/service/consulta.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

import { Consulta } from 'src/app/core/bean/consulta/consulta';
import { faCircleInfo } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-consulta-fila-completa',
  templateUrl: './consulta-fila-completa.component.html',
  styleUrls: ['./consulta-fila-completa.component.css']
})
export class ConsultaFilaCompletaComponent {
  
  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo
  }

  consultaFilaFiltro : ConsultaFilaCompletaFiltro = {
    data : '',
    turno: ''
  }

  consultas : Consulta[] = [];

  constructor( 
    private actRoute: ActivatedRoute,
    private consultaService: ConsultaService,
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

    this.consultaFilaFiltro.data = ano+'-'+mes+'-'+dia;
    this.consultaFilaFiltro.turno = ''+turno;

    this.consultaService.listaFilaCompleta( clinicaId, profissionalId, this.consultaFilaFiltro ).subscribe( {
      next: (resp) => {
        this.consultas = resp;

        if ( this.consultas.length == 0 )
          this.infoMsg = "Nenhuma consulta agendada para esta data e turno.";
          
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    } );    
  }

}
