import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faAnglesLeft, faAnglesRight, faEye, faSave } from '@fortawesome/free-solid-svg-icons';
import { AnamneseModelo } from 'src/app/core/bean/anamnese-modelo/anamnese-modelo';
import { Anamnese } from 'src/app/core/bean/anamnese/anamnese';
import { AnamneseService } from 'src/app/core/service/anamnese.service';
import { RelatorioService } from 'src/app/core/service/relatorio.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-paciente-anamnese',
  templateUrl: './paciente-anamnese.component.html',
  styleUrls: ['./paciente-anamnese.component.css']
})
export class PacienteAnamneseComponent {

  erroMsg : any = null;
  infoMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faAnglesLeft : faAnglesLeft,
    faAnglesRight : faAnglesRight,
    faEye : faEye
  }

  anamnese : Anamnese = {
    id: 0,
    nome: '',
    dataCriacao: '',
    perguntas : []
  }

  anamneseModeloId : number = 0;
  anamneseModelosIDs : number[] = [];
  anamneseModelosNomes : number[] = [];

  pacienteNome : string = '';
  anamnesePreenchida : boolean = false;

  constructor( 
    private actRoute : ActivatedRoute,
    private anamneseService: AnamneseService, 
    private relatorioService: RelatorioService,
    private sistemaService: SistemaService ) {}

  ngOnInit() {
    this.carrega();
  }
  
  carrega() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    let anamneseId = this.actRoute.snapshot.paramMap.get( 'anamneseId' );

    if ( anamneseId == '-1' ) {
      this.anamneseService.loadRegTela().subscribe({
        next: ( resp ) => {
          this.anamneseModelosIDs = resp.anamneseModelos.ids;
          this.anamneseModelosNomes = resp.anamneseModelos.nomes;
         
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    } else {
      this.anamneseService.loadEditTela( anamneseId ).subscribe({
        next: ( resp ) => {
          this.anamnese = resp.anamnese;
          this.anamneseModelosIDs = resp.anamneseModelos.ids;
          this.anamneseModelosNomes = resp.anamneseModelos.nomes;
         
          this.showSpinner = false;
        },
        error: (erro) => {
          this.erroMsg = this.sistemaService.mensagemErro( erro );
          this.showSpinner = false;
        }
      });
    }
  }

  salva() {    
    this.infoMsg = null;
    this.erroMsg = null;
    
    this.showSpinner = true;

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'id' );    

      
  }

  downloadRelatorio() {
    this.infoMsg = null;
    this.erroMsg = null;

    if ( this.anamnesePreenchida == false ) {
      this.infoMsg = "A anamnese do paciente não foi preenchida ainda.";
      return;
    }

    this.showSpinner = true;

    let pacienteId = this.actRoute.snapshot.paramMap.get( 'id' );

    this.relatorioService.getRelatorioAnamnese( pacienteId ).subscribe({
      next: (resp) => {
        this.sistemaService.criaDownloadAncora( resp, 'anamnese.pdf' );
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });  
  }

}
