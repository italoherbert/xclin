import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  username : string = '';
  perfil : string = '';

  constructor() {}

  ngOnInit() {
    this.username = ''+localStorage.getItem( 'username' );
    this.perfil = ''+localStorage.getItem( 'perfil' );
  }

}
