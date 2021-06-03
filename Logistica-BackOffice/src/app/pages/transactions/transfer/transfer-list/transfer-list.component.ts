import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {InputsService} from '../../../../core/services/inputs.service';
import {State} from '@progress/kendo-data-query';
import {BreadCrumb} from '../../../../core/models/all.models';


@Component({
  selector: 'app-transfer-list',
  templateUrl: './transfer-list.component.html',
  styleUrls: ['./transfer-list.component.scss']
})

export class TransferListComponent implements OnInit {
  breadCrumb: BreadCrumb;
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };

  constructor(
    public formBuilder: FormBuilder,
    private service: InputsService) {
  }


  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Transfers',
      items: [
        {label: 'Transfers', active: true}
      ]
    };
    // todo: use simple tables
    this.service.getAll().subscribe(data => this.gridData = data);
  }

  public onStateChange(state: State): void {
    this.gridState = state;
  }
}

