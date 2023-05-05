import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecursoSaveComponent } from './recurso-save.component';

describe('RecursoSaveComponent', () => {
  let component: RecursoSaveComponent;
  let fixture: ComponentFixture<RecursoSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecursoSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecursoSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
