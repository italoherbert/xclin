import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faCircleInfo, faEye, faPlusCircle, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { ClinicaService } from 'src/app/core/service/clinica.service';
import { SistemaService } from 'src/app/core/service/sistema.service';

@Component({
  selector: 'app-nao-admin-clinica-tela',
  templateUrl: './nao-admin-clinica-tela.component.html',
  styleUrls: ['./nao-admin-clinica-tela.component.css']
})
export class NaoAdminClinicaTelaComponent {

  infoMsg : any = null;
  erroMsg : any = null;

  showSpinner : boolean = false;

  icons : any = {
    faCircleInfo : faCircleInfo,
    faEye : faEye,
    faTrashCan : faTrashCan,
    faPlusCircle : faPlusCircle
  }

  clinicasIDs : number[] = [];
  clinicasNomes : string[] = [];
  clinicaId : number = 0;

  constructor( 
    private router: Router,
    private clinicaService: ClinicaService, 
    private sistemaService: SistemaService) {}

  ngOnInit() {
    this.infoMsg = null;
    this.erroMsg = null;

    this.showSpinner = true;

    this.clinicaService.getTelaClinicaNaoAdmin().subscribe({
      next: (resp) => {
        this.clinicasIDs = resp.clinicasIDs;
        this.clinicasNomes = resp.clinicasNomes;

        if ( this.clinicasIDs.length > 0 )
          this.clinicaId = this.clinicasIDs[ 0 ];

        this.showSpinner = false;
      },
      error: (erro) => {
        this.erroMsg = this.sistemaService.mensagemErro( erro );
        this.showSpinner = false;
      }
    });
  }

  visualiza() {
    this.router.navigate([ '/app', { outlets : { page : 'clinica-detalhes/'+this.clinicaId } } ]);
  }


}
