import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoIniciadoComponent } from './atendimento-iniciado.component';

describe('AtendimentoIniciadoComponent', () => {
  let component: AtendimentoIniciadoComponent;
  let fixture: ComponentFixture<AtendimentoIniciadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoIniciadoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoIniciadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
