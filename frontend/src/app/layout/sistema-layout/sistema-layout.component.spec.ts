import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SistemaLayoutComponent } from './sistema-layout.component';

describe('SistemaLayoutComponent', () => {
  let component: SistemaLayoutComponent;
  let fixture: ComponentFixture<SistemaLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SistemaLayoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SistemaLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
