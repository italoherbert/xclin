import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnamneseModeloSaveComponent } from './anamnese-modelo-save.component';

describe('AnamneseModeloSaveComponent', () => {
  let component: AnamneseModeloSaveComponent;
  let fixture: ComponentFixture<AnamneseModeloSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnamneseModeloSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnamneseModeloSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
