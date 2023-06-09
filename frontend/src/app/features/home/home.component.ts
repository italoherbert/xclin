import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  username : string = '';
  perfilLabel : string = '';

  constructor() {}

  ngOnInit() {
    this.username = ''+localStorage.getItem( 'username' );
    this.perfilLabel = ''+localStorage.getItem( 'perfil-label' );
  }

}
