import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoRegistroComponent } from './atendimento-registro.component';

describe('AtendimentoRegistroComponent', () => {
  let component: AtendimentoRegistroComponent;
  let fixture: ComponentFixture<AtendimentoRegistroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoRegistroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoRegistroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
