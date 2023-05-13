import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaDetalhesComponent } from './consulta-detalhes.component';

describe('ConsultaDetalhesComponent', () => {
  let component: ConsultaDetalhesComponent;
  let fixture: ComponentFixture<ConsultaDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
