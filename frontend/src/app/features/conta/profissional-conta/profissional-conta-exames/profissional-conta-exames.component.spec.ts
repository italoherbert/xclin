import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalContaExamesComponent } from './profissional-conta-exames.component';

describe('ProfissionalContaExamesComponent', () => {
  let component: ProfissionalContaExamesComponent;
  let fixture: ComponentFixture<ProfissionalContaExamesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalContaExamesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalContaExamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
