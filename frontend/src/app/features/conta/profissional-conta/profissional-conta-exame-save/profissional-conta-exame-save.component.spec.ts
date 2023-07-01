import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalContaExameSaveComponent } from './profissional-conta-exame-save.component';

describe('ProfissionalContaExameSaveComponent', () => {
  let component: ProfissionalContaExameSaveComponent;
  let fixture: ComponentFixture<ProfissionalContaExameSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalContaExameSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalContaExameSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
