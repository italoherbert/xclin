import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoAlterarComponent } from './atendimento-alterar.component';

describe('AtendimentoAlterarComponent', () => {
  let component: AtendimentoAlterarComponent;
  let fixture: ComponentFixture<AtendimentoAlterarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoAlterarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoAlterarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
