import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UniteListComponent} from './unite-list.component';

describe('ProductListComponent', () => {
  let component: UniteListComponent;
  let fixture: ComponentFixture<UniteListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UniteListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UniteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
