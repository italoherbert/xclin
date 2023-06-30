import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoFilaCompletaComponent } from './atendimento-fila-completa.component';

describe('AtendimentoFilaCompletaComponent', () => {
  let component: AtendimentoFilaCompletaComponent;
  let fixture: ComponentFixture<AtendimentoFilaCompletaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoFilaCompletaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoFilaCompletaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
