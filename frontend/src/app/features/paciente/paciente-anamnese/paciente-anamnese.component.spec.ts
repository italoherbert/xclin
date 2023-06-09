import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteAnamneseComponent } from './paciente-anamnese.component';

describe('PacienteAnamneseComponent', () => {
  let component: PacienteAnamneseComponent;
  let fixture: ComponentFixture<PacienteAnamneseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteAnamneseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PacienteAnamneseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
