import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoFiltroComponent } from './atendimento-filtro.component';

describe('AtendimentoFiltroComponent', () => {
  let component: AtendimentoFiltroComponent;
  let fixture: ComponentFixture<AtendimentoFiltroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoFiltroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoFiltroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
