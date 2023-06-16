import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioClinicaVinculosComponent } from './usuario-clinica-vinculos.component';

describe('UsuarioClinicaVinculosComponent', () => {
  let component: UsuarioClinicaVinculosComponent;
  let fixture: ComponentFixture<UsuarioClinicaVinculosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioClinicaVinculosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioClinicaVinculosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
