import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Exame } from 'src/app/core/bean/exame/exame';
import { ContaProfissionalService } from 'src/app/core/service/conta-profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-profissional-conta-exames',
  templateUrl: './profissional-conta-exames.component.html',
  styleUrls: ['./profissional-conta-exames.component.css']
})
export class ProfissionalContaExamesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare: faPenToSquare
  }

  examesColumns : string[] = [ 'nome', 'valorCobrado' ];
  examesDataSource = new MatTableDataSource<any>([])

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

    this.contaProfissionalService.listaExamesVinculos().subscribe({
      next: (resp) => {
        this.examesDataSource.data = resp;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}
