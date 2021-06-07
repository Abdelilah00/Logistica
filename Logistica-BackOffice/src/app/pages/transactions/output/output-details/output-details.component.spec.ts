import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {OutputDetailsComponent} from './output-details.component';

describe('TransactionDetalsComponent', () => {
  let component: OutputDetailsComponent;
  let fixture: ComponentFixture<OutputDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [OutputDetailsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutputDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
