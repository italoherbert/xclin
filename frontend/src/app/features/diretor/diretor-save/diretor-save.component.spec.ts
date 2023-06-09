import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiretorSaveComponent } from './diretor-save.component';

describe('DiretorSaveComponent', () => {
  let component: DiretorSaveComponent;
  let fixture: ComponentFixture<DiretorSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiretorSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiretorSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
