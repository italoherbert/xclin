import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NaoAdminClinicaTelaComponent } from './nao-admin-clinica-tela.component';

describe('NaoAdminClinicaTelaComponent', () => {
  let component: NaoAdminClinicaTelaComponent;
  let fixture: ComponentFixture<NaoAdminClinicaTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NaoAdminClinicaTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NaoAdminClinicaTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
