import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExameTelaComponent } from './exame-tela.component';

describe('ExameTelaComponent', () => {
  let component: ExameTelaComponent;
  let fixture: ComponentFixture<ExameTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExameTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExameTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
