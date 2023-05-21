import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EspecialidadeTelaComponent } from './especialidade-tela.component';

describe('EspecialidadeTelaComponent', () => {
  let component: EspecialidadeTelaComponent;
  let fixture: ComponentFixture<EspecialidadeTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EspecialidadeTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EspecialidadeTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
