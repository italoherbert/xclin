import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoTelaComponent } from './atendimento-tela.component';

describe('AtendimentoTelaComponent', () => {
  let component: AtendimentoTelaComponent;
  let fixture: ComponentFixture<AtendimentoTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
