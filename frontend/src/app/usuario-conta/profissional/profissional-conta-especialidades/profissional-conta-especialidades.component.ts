import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faEdit, faPenToSquare, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { EspecialidadeService } from 'src/app/service/especialidade.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { SistemaService } from 'src/app/service/sistema.service';

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
    private matDialog: MatDialog,
    private especialidadeService: EspecialidadeService,
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