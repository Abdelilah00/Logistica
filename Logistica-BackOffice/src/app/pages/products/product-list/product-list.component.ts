import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../core/models/all.models';
import {State} from '@progress/kendo-data-query';
import {FormBuilder} from '@angular/forms';
import {ProductsService} from '../../../core/services/products.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  breadCrumb: BreadCrumb;
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };

  constructor(
    public formBuilder: FormBuilder,
    private service: ProductsService) {
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Products',
      items: [
        {label: 'Products', active: true}
      ]
    };
    // todo: use simple tables
    this.service.getAll().subscribe(data => this.gridData = data);
  }

  public onStateChange(state: State): void {
    this.gridState = state;
  }
}
