import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AuditListComponent} from './audit-list.component';

describe('SupplierListComponent', () => {
  let component: AuditListComponent;
  let fixture: ComponentFixture<AuditListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AuditListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuditListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
