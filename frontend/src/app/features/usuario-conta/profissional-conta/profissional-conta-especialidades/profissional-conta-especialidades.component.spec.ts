import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalContaEspecialidadesComponent } from './profissional-conta-especialidades.component';

describe('ProfissionalContaEspecialidadesComponent', () => {
  let component: ProfissionalContaEspecialidadesComponent;
  let fixture: ComponentFixture<ProfissionalContaEspecialidadesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalContaEspecialidadesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalContaEspecialidadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
