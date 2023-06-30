import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoAgendaComponent } from './atendimento-agenda.component';

describe('AtendimentoAgendaComponent', () => {
  let component: AtendimentoAgendaComponent;
  let fixture: ComponentFixture<AtendimentoAgendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoAgendaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoAgendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
