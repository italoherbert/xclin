import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalContaDetalhesComponent } from './profissional-conta-detalhes.component';

describe('ProfissionalContaDetalhesComponent', () => {
  let component: ProfissionalContaDetalhesComponent;
  let fixture: ComponentFixture<ProfissionalContaDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalContaDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalContaDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
