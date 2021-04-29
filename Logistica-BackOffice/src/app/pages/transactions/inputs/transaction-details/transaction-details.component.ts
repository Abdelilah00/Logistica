import {Component, Input, OnInit} from '@angular/core';
import {TransactionDetail} from '../../../../core/models/auth.models';
import {TransactionDetailsService} from '../../../../core/services/transaction-details.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';


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

  //customersData: TransactionDetail[];
  validationForm: FormGroup;

  constructor(private transactionDetailsService: TransactionDetailsService,
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
    const tmp = new TransactionDetail();
    tmp.inputId = this.inputId;
    this.editDataItem = tmp;
    this.isNew = true;
  }
}
