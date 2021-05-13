import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {GridComponent} from '@progress/kendo-angular-grid';
import {TransactionDetail} from '../../../../core/models/all.models';

@Component({
  selector: 'app-transaction-details-create',
  templateUrl: './transaction-details-create.component.html',
  styleUrls: ['./transaction-details-create.component.scss']
})
export class TransactionDetailsCreateComponent implements OnInit {
  products = Array<TransactionDetail>();
  originalProducts = [];
  productsForm: FormGroup;
  isEditMode = true;
  @ViewChild('grid') grid: GridComponent;
  @Output() transactionDetailsSave = new EventEmitter<any>();

  constructor(private formBuilder: FormBuilder) {
  }

  // convenience getters for easy access to form fields
  get f() {
    return this.productsForm.controls;
  }

  get fa() {
    return this.f.formArray as FormArray;
  }

  ngOnInit() {
    // initialise products form with empty form array
    this.productsForm = this.formBuilder.group({
      formArray: new FormArray([])
    });
  }

  onEdit() {
    // store copy of original products in case cancelled
    this.originalProducts = [...this.products];

    // reset / initialise form fields
    this.resetForm();

    // set all rows to edit mode to display form fields
    this.editAllRows();
    this.isEditMode = true;
  }

  onAdd() {
    // add item to products array
    this.products.push(new TransactionDetail());

    // add new form group to form array
    let test = new TransactionDetail();
    test.qte = 10;
    test.article = 10;
    test.lot = 10;
    test.productName = 'test A Prod';
    test.priceHT = 50;
    test.tVa = 0.20;
    test.expDate = new Date();
    const formGroup = this.createFormGroup(test);
    this.fa.push(formGroup);

    // set new row to edit mode in kendo grid
    this.grid.editRow(this.products.length - 1, formGroup);
  }

  onRemove(index) {
    // rows must all be closed while removing products
    this.closeAllRows();

    // remove product and product form group
    this.products.splice(index, 1);
    this.fa.removeAt(index);

    // reset all rows back to edit mode
    this.editAllRows();
  }

  onSave() {
    // mark all fields as touched to highlight any invalid fields
    this.productsForm.markAllAsTouched();

    // stop here if form is invalid
    if (this.productsForm.invalid) {
      alert('FORM INVALID :(');
      return;
    }

    // copy form data to products array on success
    this.products = this.fa.value;
    this.closeAllRows();
    this.isEditMode = false;
    this.transactionDetailsSave.emit(this.productsForm);
  }

  onCancel() {
    this.closeAllRows();

    // reset products back to original data (before edit was clicked)
    this.products = this.originalProducts;

    this.isEditMode = false;
  }

  // helper methods

  public createFormGroup(dataItem: TransactionDetail = new TransactionDetail()): FormGroup {
    return this.formBuilder.group({
      productName: [dataItem.productName, Validators.required],
      lot: [dataItem.lot, Validators.required],
      article: [dataItem.article, Validators.required],
      qte: [dataItem.qte, Validators.required],
      tVA: [dataItem.tVa, Validators.required],
      expDate: [dataItem.expDate, Validators.required],
      priceHT: [dataItem.priceHT, Validators.required],
    });
  }

  private editAllRows() {
    // set all rows to edit mode to display form fields
    this.products.forEach((x, i) => {
      // @ts-ignore
      this.grid.editRow(i, this.fa.controls[i]);
    });
  }

  private closeAllRows() {
    // close all rows to display readonly view of data
    this.products.forEach((x, i) => {
      this.grid.closeRow(i);
    });
  }

  private resetForm() {
    // clear form array and create a new form group for each product
    this.fa.clear();
    this.products.forEach((x, i) => {
      this.fa.push(this.createFormGroup(x));
    });
  }


}
