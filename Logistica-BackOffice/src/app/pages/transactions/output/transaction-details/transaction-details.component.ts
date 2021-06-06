import {Component, Input, OnInit} from '@angular/core';
import {TransactionDetail} from '../../../../core/models/all.models';
import {TransactionDetailsService} from '../../../../core/services/Products/transaction-details.service';
import {FormBuilder, FormGroup} from '@angular/forms';


@Component({
  selector: 'app-transaction-details',
  templateUrl: './transaction-details.component.html',
  styleUrls: ['./transaction-details.component.scss']
})

export class TransactionDetailsComponent implements OnInit {
  view: TransactionDetail[];
  @Input() inputId: number | null;
  public editDataItem: TransactionDetail;
  public isNew: boolean;

  submitted: boolean;

  // customersData: TransactionDetail[];
  validationForm: FormGroup;

  constructor(private transactionDetailsService: TransactionDetailsService,
              public formBuilder: FormBuilder) {
  }

  get form() {
    return this.validationForm.controls;
  }


  ngOnInit(): void {
    console.log(this.inputId);
    if (this.inputId !== null) {
      this.transactionDetailsService.getByOutputId(this.inputId).subscribe(data => this.view = data);
    }
  }

  public addHandler(): void {
    const tmp = new TransactionDetail();
    tmp.inputId = this.inputId;
    this.editDataItem = tmp;
    this.isNew = true;
  }
}
