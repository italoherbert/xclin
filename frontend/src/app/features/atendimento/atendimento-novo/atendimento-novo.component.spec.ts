import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoNovoComponent } from './atendimento-novo.component';

describe('AtendimentoNovoComponent', () => {
  let component: AtendimentoNovoComponent;
  let fixture: ComponentFixture<AtendimentoNovoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtendimentoNovoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtendimentoNovoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
