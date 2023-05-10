import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecepcionistaDetalhesComponent } from './recepcionista-detalhes.component';

describe('RecepcionistaDetalhesComponent', () => {
  let component: RecepcionistaDetalhesComponent;
  let fixture: ComponentFixture<RecepcionistaDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecepcionistaDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecepcionistaDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
