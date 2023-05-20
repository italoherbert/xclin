import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiretorContaAlterarComponent } from './diretor-conta-alterar.component';

describe('DiretorContaAlterarComponent', () => {
  let component: DiretorContaAlterarComponent;
  let fixture: ComponentFixture<DiretorContaAlterarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiretorContaAlterarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiretorContaAlterarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
