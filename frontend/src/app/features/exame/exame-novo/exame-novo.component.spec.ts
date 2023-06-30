import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExameNovoComponent } from './exame-novo.component';

describe('ExameNovoComponent', () => {
  let component: ExameNovoComponent;
  let fixture: ComponentFixture<ExameNovoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExameNovoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExameNovoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
