import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TransactionDetailsCreateComponent} from './transaction-details-create.component';

describe('TransactionDetailsCreateComponent', () => {
  let component: TransactionDetailsCreateComponent;
  let fixture: ComponentFixture<TransactionDetailsCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TransactionDetailsCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionDetailsCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
