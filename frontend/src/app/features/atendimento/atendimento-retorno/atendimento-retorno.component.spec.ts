import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoRetornoComponent } from './atendimento-retorno.component';

describe('AtendimentoRetornoComponent', () => {
  let component: AtendimentoRetornoComponent;
  let fixture: ComponentFixture<AtendimentoRetornoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoRetornoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoRetornoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
