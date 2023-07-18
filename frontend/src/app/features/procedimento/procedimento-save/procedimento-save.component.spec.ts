import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcedimentoSaveComponent } from './procedimento-save.component';

describe('ProcedimentoSaveComponent', () => {
  let component: ProcedimentoSaveComponent;
  let fixture: ComponentFixture<ProcedimentoSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcedimentoSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProcedimentoSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
