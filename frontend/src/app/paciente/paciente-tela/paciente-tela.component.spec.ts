import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteTelaComponent } from './paciente-tela.component';

describe('PacienteTelaComponent', () => {
  let component: PacienteTelaComponent;
  let fixture: ComponentFixture<PacienteTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PacienteTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
