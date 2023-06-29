import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicaExameDetalhesComponent } from './clinica-exame-detalhes.component';

describe('ClinicaExameDetalhesComponent', () => {
  let component: ClinicaExameDetalhesComponent;
  let fixture: ComponentFixture<ClinicaExameDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClinicaExameDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClinicaExameDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
