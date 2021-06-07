import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {State} from '@progress/kendo-data-query';
import {FormBuilder} from '@angular/forms';
import {StocksService} from '../../../../core/services/Products/stocks.service';
import {CategoryService} from '../../../../core/services/Products/category.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {

  breadCrumb: BreadCrumb;
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };

  constructor(
    public formBuilder: FormBuilder,
    private service: CategoryService) {
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Categories',
      items: [
        {label: 'Categories', active: true}
      ]
    };
    // todo: use simple tables
    this.service.getAll().subscribe(data => this.gridData = data);
  }

  public onStateChange(state: State): void {
    this.gridState = state;
  }
}
