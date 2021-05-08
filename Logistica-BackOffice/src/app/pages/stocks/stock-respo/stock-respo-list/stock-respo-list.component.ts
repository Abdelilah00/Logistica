import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/auth.models';
import {State} from '@progress/kendo-data-query';
import {FormBuilder} from '@angular/forms';
import {StocksService} from '../../../../core/services/stocks.service';

@Component({
  selector: 'app-stock-list',
  templateUrl: './stock-respo-list.component.html',
  styleUrls: ['./stock-respo-list.component.scss']
})
export class StockRespoListComponent implements OnInit {

  breadCrumb: BreadCrumb;
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };

  constructor(
    public formBuilder: FormBuilder,
    private service: StocksService) {
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Stocks',
      items: [
        {label: 'Stocks', active: true}
      ]
    };
    // todo: use simple tables
    this.service.getAll().subscribe(data => this.gridData = data);
  }

  public onStateChange(state: State): void {
    this.gridState = state;
  }
}
