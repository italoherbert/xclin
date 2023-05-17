import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaProfissionalSelectComponent } from './consulta-profissional-select.component';

describe('ConsultaProfissionalSelectComponent', () => {
  let component: ConsultaProfissionalSelectComponent;
  let fixture: ComponentFixture<ConsultaProfissionalSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaProfissionalSelectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaProfissionalSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
