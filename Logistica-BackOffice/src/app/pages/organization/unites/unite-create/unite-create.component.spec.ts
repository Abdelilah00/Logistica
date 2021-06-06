import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UniteCreateComponent} from './unite-create.component';

describe('ProductCreateComponent', () => {
  let component: UniteCreateComponent;
  let fixture: ComponentFixture<UniteCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UniteCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UniteCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
