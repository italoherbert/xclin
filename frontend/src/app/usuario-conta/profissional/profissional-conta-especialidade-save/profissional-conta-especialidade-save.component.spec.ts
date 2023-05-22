import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalContaEspecialidadeSaveComponent } from './profissional-conta-especialidade-save.component';

describe('ProfissionalContaEspecialidadeSaveComponent', () => {
  let component: ProfissionalContaEspecialidadeSaveComponent;
  let fixture: ComponentFixture<ProfissionalContaEspecialidadeSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalContaEspecialidadeSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalContaEspecialidadeSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
