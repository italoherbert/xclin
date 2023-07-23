import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicaAutocompleteInputComponent } from './clinica-autocomplete-input.component';

describe('ClinicaAutocompleteInputComponent', () => {
  let component: ClinicaAutocompleteInputComponent;
  let fixture: ComponentFixture<ClinicaAutocompleteInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClinicaAutocompleteInputComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClinicaAutocompleteInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
