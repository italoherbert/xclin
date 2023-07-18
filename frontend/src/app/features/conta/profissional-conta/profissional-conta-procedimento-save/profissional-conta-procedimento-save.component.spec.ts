import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalContaProcedimentoSaveComponent } from './profissional-conta-procedimento-save.component';

describe('ProfissionalContaProcedimentoSaveComponent', () => {
  let component: ProfissionalContaProcedimentoSaveComponent;
  let fixture: ComponentFixture<ProfissionalContaProcedimentoSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalContaProcedimentoSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalContaProcedimentoSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
