import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultasDiaComponent } from './consultas-dia.component';

describe('ConsultasDiaComponent', () => {
  let component: ConsultasDiaComponent;
  let fixture: ComponentFixture<ConsultasDiaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultasDiaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultasDiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
