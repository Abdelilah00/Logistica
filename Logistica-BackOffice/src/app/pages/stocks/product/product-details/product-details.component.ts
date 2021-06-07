import {Component, Input, OnInit} from '@angular/core';
import {Product, Stock} from '../../../../core/models/all.models';
import {ProductsService} from '../../../../core/services/Products/products.service';
import {StocksService} from '../../../../core/services/Products/stocks.service';


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})

export class ProductDetailsComponent implements OnInit {
  view: Stock[];
  @Input() inputId: number | null;

  constructor(private stocksService: StocksService) {
  }

  ngOnInit(): void {
    if (this.inputId !== null) {
      this.stocksService.getByProductId(this.inputId).subscribe(data => this.view = data);
    }

  }

}
