import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {State} from '@progress/kendo-data-query';
import {FormBuilder} from '@angular/forms';
import {ActorService} from '../../../../core/services/actor.service';

@Component({
  selector: 'app-supplier-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {
  breadCrumb: BreadCrumb;
  gridData;
  public gridState: State = {
    sort: [],
    skip: 0,
    take: 10
  };

  constructor(
    public formBuilder: FormBuilder,
    private service: ActorService) {
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Clients',
      items: [
        {label: 'Clients', active: true}
      ]
    };
    // todo: use simple tables
    this.service.getClients().subscribe(data => this.gridData = data);
  }

  public onStateChange(state: State): void {
    this.gridState = state;
  }
}
