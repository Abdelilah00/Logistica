import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-input-create',
  templateUrl: './input-create.component.html',
  styleUrls: ['./input-create.component.scss']
})
export class InputCreateComponent implements OnInit {

  breadCrumbItems: Array<{}>;
  selectValue = ['Touchscreen', 'Call Function', 'Notifications', 'Fitness', 'Outdoor'];

  constructor() {
  }

  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Ecommerce'}, {label: 'Add Product', active: true}];
  }
}
