import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecepcionistaContaDetalhesComponent } from './recepcionista-conta-detalhes.component';

describe('RecepcionistaContaDetalhesComponent', () => {
  let component: RecepcionistaContaDetalhesComponent;
  let fixture: ComponentFixture<RecepcionistaContaDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecepcionistaContaDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecepcionistaContaDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
