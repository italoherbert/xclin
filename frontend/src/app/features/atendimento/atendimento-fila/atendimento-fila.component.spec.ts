import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoFilaComponent } from './atendimento-fila.component';

describe('AtendimentoFilaComponent', () => {
  let component: AtendimentoFilaComponent;
  let fixture: ComponentFixture<AtendimentoFilaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoFilaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoFilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
