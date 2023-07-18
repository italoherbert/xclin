import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalContaProcedimentosComponent } from './profissional-conta-procedimentos.component';

describe('ProfissionalContaProcedimentosComponent', () => {
  let component: ProfissionalContaProcedimentosComponent;
  let fixture: ComponentFixture<ProfissionalContaProcedimentosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalContaProcedimentosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalContaProcedimentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
