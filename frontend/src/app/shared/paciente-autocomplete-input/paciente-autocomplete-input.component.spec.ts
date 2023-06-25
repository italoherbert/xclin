import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteAutocompleteInputComponent } from './paciente-autocomplete-input.component';

describe('PacienteAutocompleteInputComponent', () => {
  let component: PacienteAutocompleteInputComponent;
  let fixture: ComponentFixture<PacienteAutocompleteInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteAutocompleteInputComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PacienteAutocompleteInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
