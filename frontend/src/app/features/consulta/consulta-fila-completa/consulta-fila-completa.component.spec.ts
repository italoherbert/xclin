import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaFilaCompletaComponent } from './consulta-fila-completa.component';

describe('ConsultaFilaCompletaComponent', () => {
  let component: ConsultaFilaCompletaComponent;
  let fixture: ComponentFixture<ConsultaFilaCompletaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaFilaCompletaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaFilaCompletaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
