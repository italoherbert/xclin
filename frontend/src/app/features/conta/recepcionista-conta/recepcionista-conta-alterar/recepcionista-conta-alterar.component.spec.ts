import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecepcionistaContaAlterarComponent } from './recepcionista-conta-alterar.component';

describe('RecepcionistaContaAlterarComponent', () => {
  let component: RecepcionistaContaAlterarComponent;
  let fixture: ComponentFixture<RecepcionistaContaAlterarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecepcionistaContaAlterarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecepcionistaContaAlterarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
