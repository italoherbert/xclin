import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaAlterarComponent } from './consulta-alterar.component';

describe('ConsultaAlterarComponent', () => {
  let component: ConsultaAlterarComponent;
  let fixture: ComponentFixture<ConsultaAlterarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaAlterarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaAlterarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
