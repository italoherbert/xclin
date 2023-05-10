import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalSaveComponent } from './profissional-save.component';

describe('ProfissionalSaveComponent', () => {
  let component: ProfissionalSaveComponent;
  let fixture: ComponentFixture<ProfissionalSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
