import {Component, OnInit} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder} from '@angular/forms';
import {InputService} from '../../../../core/services/input.service';
import {State} from '@progress/kendo-data-query';
import {BreadCrumb} from '../../../../core/models/auth.models';


@Component({
  selector: 'app-input-list',
  templateUrl: './input-list.component.html',
  styleUrls: ['./input-list.component.scss']
})

export class InputListComponent implements OnInit {
  breadCrumb: BreadCrumb;
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };

  constructor(private modalService: NgbModal,
              public formBuilder: FormBuilder,
              private inputService: InputService) {
  }


  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Inputs',
      items: [
        {label: 'Inputs', active: true}
      ]
    };
    // todo: use simple tables
    this.inputService.getAll().subscribe(data => this.gridData = data);
  }

  public onStateChange(state: State): void {
    this.gridState = state;
  }
}

