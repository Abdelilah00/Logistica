import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StockRespoListComponent} from './stock-respo-list.component';

describe('StockListComponent', () => {
  let component: StockRespoListComponent;
  let fixture: ComponentFixture<StockRespoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StockRespoListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockRespoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
