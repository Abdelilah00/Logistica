import {Component, OnInit} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder} from '@angular/forms';
import {InputsService} from '../../../../core/services/inputs.service';
import {State} from '@progress/kendo-data-query';
import {BreadCrumb} from '../../../../core/models/auth.models';
import {OutputsService} from '../../../../core/services/outputs.service';


@Component({
  selector: 'app-input-list',
  templateUrl: './input-list.component.html',
  styleUrls: ['./input-list.component.scss']
})

export class InputListComponent implements OnInit {
  breadCrumb: BreadCrumb;
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };

  constructor(private modalService: NgbModal,
              public formBuilder: FormBuilder,
              private service: OutputsService) {
  }


  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Outputs',
      items: [
        {label: 'Outputs', active: true}
      ]
    };
    // todo: use simple tables
    this.service.getAll().subscribe(data => this.gridData = data);
  }

  public onStateChange(state: State): void {
    this.gridState = state;
  }
}

