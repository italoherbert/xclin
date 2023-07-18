import { Component } from '@angular/core';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { ContaProfissionalService } from 'src/app/core/service/conta-profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-profissional-conta-procedimentos',
  templateUrl: './profissional-conta-procedimentos.component.html',
  styleUrls: ['./profissional-conta-procedimentos.component.css']
})
export class ProfissionalContaProcedimentosComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare: faPenToSquare
  }

  procedimentos : any[] = [];

  constructor( 
    private contaProfissionalService: ContaProfissionalService,
    private sistemaService: SistemaService
  ) {}

  ngOnInit() {
    this.lista();
  }

  lista() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.contaProfissionalService.listaProcedimentosVinculos().subscribe({
      next: (resp) => {
        this.procedimentos = resp;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
