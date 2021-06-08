import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {State} from '@progress/kendo-data-query';
import {FormBuilder} from '@angular/forms';
import {StructureUnitsService} from '../../../../core/services/Organzation/structure-units.service';

@Component({
  selector: 'app-unite-list',
  templateUrl: './unite-list.component.html',
  styleUrls: ['./unite-list.component.scss']
})
export class UniteListComponent implements OnInit {

  breadCrumb: BreadCrumb;
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };

  constructor(
    public formBuilder: FormBuilder,
    private service: StructureUnitsService) {
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Units',
      items: [
        {label: 'Units', active: true}
      ]
    };
    // todo: use simple tables
    this.service.getAll().subscribe(data => this.gridData = data);
  }

  public onStateChange(state: State): void {
    this.gridState = state;
  }
}
