import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendaRemarcarComponent } from './consulta-remarcar.component';

describe('AgendaRemarcarComponent', () => {
  let component: AgendaRemarcarComponent;
  let fixture: ComponentFixture<AgendaRemarcarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgendaRemarcarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgendaRemarcarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
