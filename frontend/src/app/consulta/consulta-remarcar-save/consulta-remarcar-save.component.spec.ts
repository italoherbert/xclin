import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaRemarcarSaveComponent } from './consulta-remarcar-save.component';

describe('ConsultaRemarcarSaveComponent', () => {
  let component: ConsultaRemarcarSaveComponent;
  let fixture: ComponentFixture<ConsultaRemarcarSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaRemarcarSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaRemarcarSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
