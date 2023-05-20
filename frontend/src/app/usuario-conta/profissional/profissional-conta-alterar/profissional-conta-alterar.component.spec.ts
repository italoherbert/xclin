import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalContaAlterarComponent } from './profissional-conta-alterar.component';

describe('ProfissionalContaAlterarComponent', () => {
  let component: ProfissionalContaAlterarComponent;
  let fixture: ComponentFixture<ProfissionalContaAlterarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalContaAlterarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalContaAlterarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
