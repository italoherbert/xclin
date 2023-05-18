import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaFilaComponent } from './consulta-fila.component';

describe('ConsultaFilaComponent', () => {
  let component: ConsultaFilaComponent;
  let fixture: ComponentFixture<ConsultaFilaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaFilaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaFilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
