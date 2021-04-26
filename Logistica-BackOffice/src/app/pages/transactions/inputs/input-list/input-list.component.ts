import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder} from '@angular/forms';
import {InputService} from '../../../../core/services/input.service';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

const ELEMENT_DATA = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];

@Component({
  selector: 'app-input-list',
  templateUrl: './input-list.component.html',
  styleUrls: ['./input-list.component.scss']
})
export class InputListComponent implements OnInit, AfterViewInit {
  breadCrumbItems: Array<{}>;
  displayedColumns: string[] = ['id', 'acteur', 'acteurType', 'stage'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
  @ViewChild(MatSort) sort: MatSort;

  constructor(private modalService: NgbModal,
              public formBuilder: FormBuilder,
              private inputService: InputService) {

  }

  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Transactions'}, {label: 'Inputs', active: true}];
    // todo: use simple tables
    // this.inputService.getAll().subscribe(data => this.dataSource = new MatTableDataSource(data));
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  openModal(content: any) {
    this.modalService.open(content, {centered: true});
  }
}

