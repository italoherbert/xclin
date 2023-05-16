import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCircleInfo } from '@fortawesome/free-solid-svg-icons';
import { Consulta } from 'src/app/bean/consulta/consulta';
import { ConsultaService } from 'src/app/service/consulta.service';
import { SistemaService } from 'src/app/service/sistema.service';

@Component({
  selector: 'app-consultas-dia',
  templateUrl: './consultas-dia.component.html',
  styleUrls: ['./consultas-dia.component.css']
})
export class ConsultasDiaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo
  }

  consultas : Consulta[] = [];

  constructor( 
    private router: Router,
    private actRoute: ActivatedRoute, 
    private consultaService: ConsultaService,
    public sistemaService: SistemaService ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = false;

    let clinicaId = this.actRoute.snapshot.paramMap.get( 'clinicaId' );
    let profissionalId = this.actRoute.snapshot.paramMap.get( 'profissionalId' );
    let dia = this.actRoute.snapshot.paramMap.get( 'dia' );
    let mes = this.actRoute.snapshot.paramMap.get( 'mes' );
    let ano = this.actRoute.snapshot.paramMap.get( 'ano' );

    this.consultaService.listaConsultasPorData( clinicaId, profissionalId, dia, mes, ano ).subscribe({
      next: (resp) => {
        this.consultas = resp;
        if ( this.consultas.length == 0 )
          this.infoMsg = "Nenhuma consulta agendada para esta data.";
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  paraNovaConsulta() {
    let clinicaId = this.actRoute.snapshot.paramMap.get( 'clinicaId' );
    let profissionalId = this.actRoute.snapshot.paramMap.get( 'profissionalId' );
    let dia = this.actRoute.snapshot.paramMap.get( 'dia' );
    let mes = this.actRoute.snapshot.paramMap.get( 'mes' );
    let ano = this.actRoute.snapshot.paramMap.get( 'ano' );

    this.router.navigate( [ '/app', { outlets : { page : 
      'consulta-save/'+clinicaId+'/'+profissionalId+'/'+dia+'/'+mes+'/'+ano
    }} ] );
  }
  
}