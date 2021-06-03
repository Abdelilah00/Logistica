import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {OutputCreateComponent} from './output-create.component';

describe('InputCreateComponent', () => {
  let component: OutputCreateComponent;
  let fixture: ComponentFixture<OutputCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [OutputCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutputCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
