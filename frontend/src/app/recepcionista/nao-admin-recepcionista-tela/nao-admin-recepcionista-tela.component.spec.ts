import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NaoAdminRecepcionistaTelaComponent } from './nao-admin-recepcionista-tela.component';

describe('NaoAdminRecepcionistaTelaComponent', () => {
  let component: NaoAdminRecepcionistaTelaComponent;
  let fixture: ComponentFixture<NaoAdminRecepcionistaTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NaoAdminRecepcionistaTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NaoAdminRecepcionistaTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
