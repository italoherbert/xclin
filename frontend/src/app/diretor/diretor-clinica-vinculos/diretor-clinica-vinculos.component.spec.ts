import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiretorClinicaVinculosComponent } from './diretor-clinica-vinculos.component';

describe('DiretorClinicaVinculosComponent', () => {
  let component: DiretorClinicaVinculosComponent;
  let fixture: ComponentFixture<DiretorClinicaVinculosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiretorClinicaVinculosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiretorClinicaVinculosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
