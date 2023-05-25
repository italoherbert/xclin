import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaFiltroResumidoComponent } from './consulta-filtro-resumido.component';

describe('ConsultaFiltroResumidoComponent', () => {
  let component: ConsultaFiltroResumidoComponent;
  let fixture: ComponentFixture<ConsultaFiltroResumidoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaFiltroResumidoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaFiltroResumidoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
