import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicaDetalhesComponent } from './clinica-detalhes.component';

describe('ClinicaDetalhesComponent', () => {
  let component: ClinicaDetalhesComponent;
  let fixture: ComponentFixture<ClinicaDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClinicaDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClinicaDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
