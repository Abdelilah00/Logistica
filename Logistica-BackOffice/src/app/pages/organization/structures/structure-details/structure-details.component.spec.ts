import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StructureDetailsComponent} from './structure-details.component';

describe('TransactionDetalsComponent', () => {
  let component: StructureDetailsComponent;
  let fixture: ComponentFixture<StructureDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StructureDetailsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StructureDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
