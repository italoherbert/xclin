import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicaExameSaveComponent } from './clinica-exame-save.component';

describe('ClinicaExameSaveComponent', () => {
  let component: ClinicaExameSaveComponent;
  let fixture: ComponentFixture<ClinicaExameSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClinicaExameSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClinicaExameSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
