import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicaExameTelaComponent } from './clinica-exame-tela.component';

describe('ClinicaExameTelaComponent', () => {
  let component: ClinicaExameTelaComponent;
  let fixture: ComponentFixture<ClinicaExameTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClinicaExameTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClinicaExameTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
