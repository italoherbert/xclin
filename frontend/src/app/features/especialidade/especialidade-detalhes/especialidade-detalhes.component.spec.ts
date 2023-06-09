import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EspecialidadeDetalhesComponent } from './especialidade-detalhes.component';

describe('EspecialidadeDetalhesComponent', () => {
  let component: EspecialidadeDetalhesComponent;
  let fixture: ComponentFixture<EspecialidadeDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EspecialidadeDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EspecialidadeDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
