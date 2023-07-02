import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoPagamentoComponent } from './atendimento-pagamento.component';

describe('AtendimentoPagamentoComponent', () => {
  let component: AtendimentoPagamentoComponent;
  let fixture: ComponentFixture<AtendimentoPagamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoPagamentoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoPagamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
