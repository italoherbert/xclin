import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalTelaComponent } from './profissional-tela.component';

describe('ProfissionalTelaComponent', () => {
  let component: ProfissionalTelaComponent;
  let fixture: ComponentFixture<ProfissionalTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfissionalTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfissionalTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
