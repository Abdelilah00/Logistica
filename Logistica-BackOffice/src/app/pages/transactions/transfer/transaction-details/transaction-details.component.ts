import {Component, Input, OnInit} from '@angular/core';
import {TransactionDetail, TransferDetails} from '../../../../core/models/all.models';
import {MatSnackBar} from '@angular/material/snack-bar';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {TransferDetailsService} from '../../../../core/services/transfer-details.service';


@Component({
  selector: 'app-transaction-details',
  templateUrl: './transaction-details.component.html',
  styleUrls: ['./transaction-details.component.scss']
})

export class TransactionDetailsComponent implements OnInit {
  view: TransferDetails[];
  @Input() inputId: number | null;
  public editDataItem: TransactionDetail;
  public isNew: boolean;

  submitted: boolean;

  validationForm: FormGroup;

  constructor(private transferDetailsService: TransferDetailsService,
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
      this.transferDetailsService.getByTransferId(this.inputId).subscribe(data => this.view = data);
    }

  }

  public addHandler(): void {
    const tmp = new TransactionDetail();
    tmp.inputId = this.inputId;
    this.editDataItem = tmp;
    this.isNew = true;
  }
}
