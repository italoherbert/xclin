import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuporteContaAlterarComponent } from './suporte-conta-alterar.component';

describe('SuporteContaAlterarComponent', () => {
  let component: SuporteContaAlterarComponent;
  let fixture: ComponentFixture<SuporteContaAlterarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuporteContaAlterarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuporteContaAlterarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
