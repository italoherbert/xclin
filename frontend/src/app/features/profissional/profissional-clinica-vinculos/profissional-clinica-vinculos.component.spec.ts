import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalClinicaVinculosComponent } from './profissional-clinica-vinculos.component';

describe('ProfissionalClinicaVinculosComponent', () => {
  let component: ProfissionalClinicaVinculosComponent;
  let fixture: ComponentFixture<ProfissionalClinicaVinculosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalClinicaVinculosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalClinicaVinculosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
