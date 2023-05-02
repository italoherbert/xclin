import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-layout',
  templateUrl: './login-layout.component.html',
  styleUrls: ['./login-layout.component.css']
})
export class LoginLayoutComponent implements OnInit {

  constructor( private router : Router ) {}

  ngOnInit() {
    //this.router.navigate([ { outlets: { center : ['lg'] } } ] );
  }

}
