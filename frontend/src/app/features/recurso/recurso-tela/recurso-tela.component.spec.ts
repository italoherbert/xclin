import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecursoTelaComponent } from './recurso-tela.component';

describe('RecursoTelaComponent', () => {
  let component: RecursoTelaComponent;
  let fixture: ComponentFixture<RecursoTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecursoTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecursoTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
