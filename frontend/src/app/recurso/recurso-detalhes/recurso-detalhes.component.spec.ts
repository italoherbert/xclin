import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecursoDetalhesComponent } from './recurso-detalhes.component';

describe('RecursoDetalhesComponent', () => {
  let component: RecursoDetalhesComponent;
  let fixture: ComponentFixture<RecursoDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecursoDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecursoDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
