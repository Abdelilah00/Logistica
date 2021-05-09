import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../core/models/auth.models';
import {State} from '@progress/kendo-data-query';
import {FormBuilder} from '@angular/forms';
import {SuppliersService} from '../../../core/services/suppliers.service';

@Component({
  selector: 'app-supplier-list',
  templateUrl: './audit-list.component.html',
  styleUrls: ['./audit-list.component.scss']
})
export class AuditListComponent implements OnInit {
  breadCrumb: BreadCrumb;
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };

  constructor(
    public formBuilder: FormBuilder,
    private service: SuppliersService) {
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Suppliers',
      items: [
        {label: 'Suppliers', active: true}
      ]
    };
    // todo: use simple tables
    this.service.getAll().subscribe(data => this.gridData = data);
  }

  public onStateChange(state: State): void {
    this.gridState = state;
  }
}
