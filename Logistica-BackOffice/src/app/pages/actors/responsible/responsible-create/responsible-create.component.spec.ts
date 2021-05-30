import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ResponsibleCreateComponent} from './responsible-create.component';

describe('SupplierCreateComponent', () => {
  let component: ResponsibleCreateComponent;
  let fixture: ComponentFixture<ResponsibleCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ResponsibleCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResponsibleCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
