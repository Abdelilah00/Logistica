import {Component, Input, OnInit} from '@angular/core';
import {InputDetail} from '../../../../core/models/all.models';
import {InputDetailsService} from '../../../../core/services/Products/input-details.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-input-details',
  templateUrl: './input-details.component.html',
  styleUrls: ['./input-details.component.scss']
})

export class InputDetailsComponent implements OnInit {
  view: InputDetail[];
  @Input() inputId: number | null;
  public editDataItem: InputDetail;
  public isNew: boolean;

  submitted: boolean;

  validationForm: FormGroup;

  constructor(private transactionDetailsService: InputDetailsService,
              private matSnackBar: MatSnackBar,
              public formBuilder: FormBuilder,
              private modalService: NgbModal) {
  }

  get form() {
    return this.validationForm.controls;
  }

  openModal(content: any) {
    this.modalService.open(content, {centered: true});
  }

  ngOnInit(): void {
    console.log(this.inputId);
    if (this.inputId !== null) {
      this.transactionDetailsService.getByInputId(this.inputId).subscribe(data => this.view = data);
    }
    /*    this.validationForm = this.formBuilder.group({
          qte: '',
          phone: '',
          balance: '',
          email: '',
          date: ''
        });*/
  }

  public addHandler(): void {
    const tmp = new InputDetail();
    tmp.inputId = this.inputId;
    this.editDataItem = tmp;
    this.isNew = true;
  }
}
