import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../../../core/models/all.models';
import {ProductsService} from '../../../../core/services/products.service';


@Component({
  selector: 'app-stock-details',
  templateUrl: './stock-details.component.html',
  styleUrls: ['./stock-details.component.scss']
})

export class StockDetailsComponent implements OnInit {
  view: Product[];
  @Input() inputId: number | null;

  constructor(private productsService: ProductsService) {
  }

  ngOnInit(): void {
    if (this.inputId !== null) {
      this.productsService.getByStockId(this.inputId).subscribe(data => this.view = data);
    }

  }

}
