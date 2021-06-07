import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../../../core/models/all.models';
import {ProductsService} from '../../../../core/services/Products/products.service';


@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.scss']
})

export class CategoryDetailsComponent implements OnInit {
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
