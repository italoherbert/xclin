import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NaoAdminDiretorTelaComponent } from './nao-admin-diretor-tela.component';

describe('NaoAdminDiretorTelaComponent', () => {
  let component: NaoAdminDiretorTelaComponent;
  let fixture: ComponentFixture<NaoAdminDiretorTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NaoAdminDiretorTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NaoAdminDiretorTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
