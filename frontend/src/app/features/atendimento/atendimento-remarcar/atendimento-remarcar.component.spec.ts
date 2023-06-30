import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoRemarcarComponent } from './atendimento-remarcar.component';

describe('AtendimentoRemarcarComponent', () => {
  let component: AtendimentoRemarcarComponent;
  let fixture: ComponentFixture<AtendimentoRemarcarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoRemarcarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoRemarcarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
