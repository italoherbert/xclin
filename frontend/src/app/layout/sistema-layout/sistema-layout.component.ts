import { Component } from '@angular/core';
import { faBox, faCircleUser, faUser, faUserGroup } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-sistema-layout',
  templateUrl: './sistema-layout.component.html',
  styleUrls: ['./sistema-layout.component.css']
})
export class SistemaLayoutComponent {

  icons : any = {
    faUser : faUser,
    faUserGroup : faUserGroup,
    faBox : faBox,
    faCircleUser : faCircleUser
  }

}
