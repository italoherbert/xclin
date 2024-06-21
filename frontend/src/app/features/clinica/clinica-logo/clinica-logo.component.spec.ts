import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicaLogoComponent } from './clinica-logo.component';

describe('ClinicaLogoComponent', () => {
  let component: ClinicaLogoComponent;
  let fixture: ComponentFixture<ClinicaLogoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClinicaLogoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClinicaLogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
