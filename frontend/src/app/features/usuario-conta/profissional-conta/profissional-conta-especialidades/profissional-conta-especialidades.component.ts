import { Component } from '@angular/core';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { ProfissionalService } from 'src/app/core/service/profissional.service';
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
  especialidades: any[] = [];     

  constructor( 
    private profissionalService: ProfissionalService,
    private sistemaService: SistemaService
  ) {}

  ngOnInit() {
    this.lista();
  }

  lista() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.profissionalService.listaEspecialidadeVinculosPorLogadoUID().subscribe({
      next: (resp) => {
        this.profissionalNome = resp.profissionalNome;
        this.profissionalFuncao = resp.profissionalFuncao;
        this.especialidades = resp.especialidades;

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

}