import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcedimentoDetalhesComponent } from './procedimento-detalhes.component';

describe('ProcedimentoDetalhesComponent', () => {
  let component: ProcedimentoDetalhesComponent;
  let fixture: ComponentFixture<ProcedimentoDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcedimentoDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProcedimentoDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
