import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoProfissionalSelectComponent } from './atendimento-profissional-select.component';

describe('AtendimentoProfissionalSelectComponent', () => {
  let component: AtendimentoProfissionalSelectComponent;
  let fixture: ComponentFixture<AtendimentoProfissionalSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoProfissionalSelectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoProfissionalSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
