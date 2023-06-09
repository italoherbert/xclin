import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicaSaveComponent } from './clinica-save.component';

describe('ClinicaSaveComponent', () => {
  let component: ClinicaSaveComponent;
  let fixture: ComponentFixture<ClinicaSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClinicaSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClinicaSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
