import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaRegistroComponent } from './consulta-registro.component';

describe('ConsultaRegistroComponent', () => {
  let component: ConsultaRegistroComponent;
  let fixture: ComponentFixture<ConsultaRegistroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaRegistroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaRegistroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
