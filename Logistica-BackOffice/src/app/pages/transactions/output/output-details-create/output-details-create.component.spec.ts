import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {OutputDetailsCreateComponent} from './output-details-create.component';

describe('TransactionDetailsCreateComponent', () => {
  let component: OutputDetailsCreateComponent;
  let fixture: ComponentFixture<OutputDetailsCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [OutputDetailsCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutputDetailsCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
