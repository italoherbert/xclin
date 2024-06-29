import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { Especialidade } from 'src/app/core/bean/especialidade/especialidade';
import { ContaProfissionalService } from 'src/app/core/service/conta-profissional.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-profissional-conta-especialidades',
  templateUrl: './profissional-conta-especialidades.component.html',
  styleUrls: ['./profissional-conta-especialidades.component.css']
})
export class ProfissionalContaEspecialidadesComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faPenToSquare: faPenToSquare
  }

  profissionalNome : string = '';
  profissionalFuncao: string = '';

  especialidadesColumns: string[] = ['nome', 'valorConsulta'];
  especialidadesDataSource = new MatTableDataSource<any>([]);

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

    this.contaProfissionalService.listaEspecialidadeVinculos().subscribe({
      next: (resp) => {
        this.profissionalNome = resp.profissionalNome;
        this.profissionalFuncao = resp.profissionalFuncao;
        this.especialidadesDataSource.data = resp.especialidades;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}