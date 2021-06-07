import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {InputDetailsCreateComponent} from './input-details-create.component';

describe('TransactionDetailsCreateComponent', () => {
  let component: InputDetailsCreateComponent;
  let fixture: ComponentFixture<InputDetailsCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [InputDetailsCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputDetailsCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
