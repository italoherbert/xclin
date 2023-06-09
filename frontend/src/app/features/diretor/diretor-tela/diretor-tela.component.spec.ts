import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiretorTelaComponent } from './diretor-tela.component';

describe('DiretorTelaComponent', () => {
  let component: DiretorTelaComponent;
  let fixture: ComponentFixture<DiretorTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiretorTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiretorTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
