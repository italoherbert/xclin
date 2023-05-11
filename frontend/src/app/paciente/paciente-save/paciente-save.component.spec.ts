import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteSaveComponent } from './paciente-save.component';

describe('PacienteSaveComponent', () => {
  let component: PacienteSaveComponent;
  let fixture: ComponentFixture<PacienteSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PacienteSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
