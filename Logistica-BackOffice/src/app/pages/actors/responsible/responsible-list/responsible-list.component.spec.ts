import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ResponsibleListComponent} from './responsible-list.component';

describe('SupplierListComponent', () => {
  let component: ResponsibleListComponent;
  let fixture: ComponentFixture<ResponsibleListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ResponsibleListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResponsibleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
