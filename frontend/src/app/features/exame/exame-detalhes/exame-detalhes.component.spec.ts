import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExameDetalhesComponent } from './exame-detalhes.component';

describe('ExameDetalhesComponent', () => {
  let component: ExameDetalhesComponent;
  let fixture: ComponentFixture<ExameDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExameDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExameDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
