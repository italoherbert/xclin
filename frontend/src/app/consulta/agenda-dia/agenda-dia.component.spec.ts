import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendaDiaComponent } from './agenda-dia.component';

describe('AgendaDiaComponent', () => {
  let component: AgendaDiaComponent;
  let fixture: ComponentFixture<AgendaDiaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgendaDiaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgendaDiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
