import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContaAlterarSenhaComponent } from './conta-alterar-senha.component';

describe('ContaAlterarSenhaComponent', () => {
  let component: ContaAlterarSenhaComponent;
  let fixture: ComponentFixture<ContaAlterarSenhaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContaAlterarSenhaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContaAlterarSenhaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
