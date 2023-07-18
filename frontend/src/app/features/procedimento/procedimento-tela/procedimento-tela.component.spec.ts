import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcedimentoTelaComponent } from './procedimento-tela.component';

describe('ProcedimentoTelaComponent', () => {
  let component: ProcedimentoTelaComponent;
  let fixture: ComponentFixture<ProcedimentoTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcedimentoTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProcedimentoTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
