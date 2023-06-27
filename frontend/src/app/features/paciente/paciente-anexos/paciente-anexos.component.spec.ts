import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteAnexosComponent } from './paciente-anexos.component';

describe('PacienteAnexosComponent', () => {
  let component: PacienteAnexosComponent;
  let fixture: ComponentFixture<PacienteAnexosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteAnexosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PacienteAnexosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
