import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StructureCreateComponent} from './structure-create.component';

describe('ProductCreateComponent', () => {
  let component: StructureCreateComponent;
  let fixture: ComponentFixture<StructureCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StructureCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StructureCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
