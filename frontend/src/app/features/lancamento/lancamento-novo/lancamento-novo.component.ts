import { Component } from '@angular/core';
import { faCircleLeft, faSave, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { LancamentoSave } from 'src/app/core/bean/lancamento/lancamento-save';
import { LancamentoService } from 'src/app/core/service/lancamento.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-lancamento-novo',
  templateUrl: './lancamento-novo.component.html',
  styleUrls: ['./lancamento-novo.component.css']
})
export class LancamentoNovoComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faSave : faSave,
    faCircleLeft : faCircleLeft
  }

  lancamentoSave : LancamentoSave = {
    tipo: '',
    valor: 0
  }

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];

  tipos : any[] = [];

  clinicaId : number = 0;

  constructor(
    private lancamentoService: LancamentoService,
    private sistemaService: SistemaService ) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.lancamentoService.getRegLoad().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;
        this.tipos = resp.lancamentoTipos;

        if ( this.clinicasIDs.length > 0 )
          this.clinicaId = this.clinicasIDs[ 0 ];

        if ( this.tipos.length > 0 )
          this.lancamentoSave.tipo = this.tipos[ 0 ].name;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  salva() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.lancamentoService.registra( this.clinicaId, this.lancamentoSave ).subscribe({
      next: (resp) => {
        this.infoMsg = "Novo lançamento criado com sucesso.";
        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  onValorAlterado( e : any ) {
    this.lancamentoSave.valor = e.valorReal;
  }

}
