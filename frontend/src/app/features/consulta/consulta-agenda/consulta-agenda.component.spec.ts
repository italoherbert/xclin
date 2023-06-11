import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaAgendaComponent } from './consulta-agenda.component';

describe('ConsultaAgendaComponent', () => {
  let component: ConsultaAgendaComponent;
  let fixture: ComponentFixture<ConsultaAgendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaAgendaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaAgendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
