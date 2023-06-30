import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExameSaveComponent } from './exame-save.component';

describe('ExameSaveComponent', () => {
  let component: ExameSaveComponent;
  let fixture: ComponentFixture<ExameSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExameSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExameSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
