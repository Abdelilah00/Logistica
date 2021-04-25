import {Component, OnInit} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder} from '@angular/forms';
import {InputService} from '../../../../core/services/input.service';

@Component({
  selector: 'app-input-list',
  templateUrl: './input-list.component.html',
  styleUrls: ['./input-list.component.scss']
})
export class InputListComponent implements OnInit {
  breadCrumbItems: Array<{}>;
  gridData: any[];
  displayedColumns = ['id', 'image', 'name', 'category', 'price', 'stockQte', 'initQte', 'active'];


  constructor(private modalService: NgbModal,
              public formBuilder: FormBuilder,
              private inputService: InputService) {
  }

  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Transactions'}, {label: 'Inputs', active: true}];
    // todo: use simple tables
    this.inputService.getAll().subscribe(data => this.gridData = data);
  }

  openModal(content: any) {
    this.modalService.open(content, {centered: true});
  }
}
