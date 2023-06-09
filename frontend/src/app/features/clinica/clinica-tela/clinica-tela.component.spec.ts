import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicaTelaComponent } from './clinica-tela.component';

describe('ClinicaTelaComponent', () => {
  let component: ClinicaTelaComponent;
  let fixture: ComponentFixture<ClinicaTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClinicaTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClinicaTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
