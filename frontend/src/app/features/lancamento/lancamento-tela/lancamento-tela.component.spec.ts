import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LancamentoTelaComponent } from './lancamento-tela.component';

describe('LancamentoTelaComponent', () => {
  let component: LancamentoTelaComponent;
  let fixture: ComponentFixture<LancamentoTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LancamentoTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LancamentoTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
