import {Component, OnInit} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-input-list',
  templateUrl: './input-list.component.html',
  styleUrls: ['./input-list.component.scss']
})
export class InputListComponent implements OnInit {
  breadCrumbItems: Array<{}>;

  constructor(private modalService: NgbModal, public formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Transactions'}, {label: 'Inputs', active: true}];
  }

  openModal(content: any) {
    this.modalService.open(content, {centered: true});
  }
}
