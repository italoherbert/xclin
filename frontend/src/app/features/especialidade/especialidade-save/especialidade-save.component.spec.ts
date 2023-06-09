import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EspecialidadeSaveComponent } from './especialidade-save.component';

describe('EspecialidadeSaveComponent', () => {
  let component: EspecialidadeSaveComponent;
  let fixture: ComponentFixture<EspecialidadeSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EspecialidadeSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EspecialidadeSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
