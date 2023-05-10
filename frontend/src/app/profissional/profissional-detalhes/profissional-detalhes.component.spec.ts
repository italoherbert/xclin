import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalDetalhesComponent } from './profissional-detalhes.component';

describe('ProfissionalDetalhesComponent', () => {
  let component: ProfissionalDetalhesComponent;
  let fixture: ComponentFixture<ProfissionalDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
