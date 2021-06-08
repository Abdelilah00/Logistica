import {Component, Input, OnInit} from '@angular/core';
import {Category} from '../../../../core/models/all.models';
import {CategoryService} from '../../../../core/services/Products/category.service';


@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.scss']
})

export class CategoryDetailsComponent implements OnInit {
  view: Category[];
  @Input() inputId: number | null;

  constructor(private service: CategoryService) {
  }

  ngOnInit(): void {
    if (this.inputId !== null) {
      this.service.getByParentId(this.inputId).subscribe(data => this.view = data);
    }

  }

}
