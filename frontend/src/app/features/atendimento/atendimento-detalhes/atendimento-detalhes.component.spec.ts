import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoDetalhesComponent } from './atendimento-detalhes.component';

describe('AtendimentoDetalhesComponent', () => {
  let component: AtendimentoDetalhesComponent;
  let fixture: ComponentFixture<AtendimentoDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
