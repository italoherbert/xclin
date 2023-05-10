import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecepcionistaSaveComponent } from './recepcionista-save.component';

describe('RecepcionistaSaveComponent', () => {
  let component: RecepcionistaSaveComponent;
  let fixture: ComponentFixture<RecepcionistaSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecepcionistaSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecepcionistaSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
