import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaSaveComponent } from './consulta-save.component';

describe('ConsultaSaveComponent', () => {
  let component: ConsultaSaveComponent;
  let fixture: ComponentFixture<ConsultaSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
