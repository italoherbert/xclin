import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LancamentoDetalhesComponent } from './lancamento-detalhes.component';

describe('LancamentoDetalhesComponent', () => {
  let component: LancamentoDetalhesComponent;
  let fixture: ComponentFixture<LancamentoDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LancamentoDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LancamentoDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
