import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StockRespoCreateComponent} from './stock-respo-create.component';

describe('StockCreateComponent', () => {
  let component: StockRespoCreateComponent;
  let fixture: ComponentFixture<StockRespoCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StockRespoCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockRespoCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
