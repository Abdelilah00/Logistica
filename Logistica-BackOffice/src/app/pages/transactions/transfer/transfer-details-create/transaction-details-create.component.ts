import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {GridComponent} from '@progress/kendo-angular-grid';
import {Product, InputDetail} from '../../../../core/models/all.models';
import {ProductsService} from '../../../../core/services/Products/products.service';

@Component({
  selector: 'app-transaction-details-create',
  templateUrl: './transaction-details-create.component.html',
  styleUrls: ['./transaction-details-create.component.scss']
})
export class TransactionDetailsCreateComponent implements OnInit {
  products = Array<InputDetail>();
  originalProducts = [];
  transactions: FormGroup;
  isEditMode = true;
  productList: Product[];

  @ViewChild('grid') grid: GridComponent;
  @Output() transactionDetailsSave = new EventEmitter<any>();

  constructor(private formBuilder: FormBuilder, private productsService: ProductsService) {
  }

  // convenience getters for easy access to form fields
  get f() {
    return this.transactions.controls;
  }

  get fa() {
    return this.f.formArray as FormArray;
  }

  ngOnInit() {
    // initialise products form with empty form array
    this.transactions = this.formBuilder.group({formArray: new FormArray([])});
    this.productsService.getAll().subscribe(data => this.productList = data);
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
    this.products.push(new InputDetail());

    // add new form group to form array
    const test = new InputDetail();
    test.productId = 0;
    test.qte = 10;
    test.article = 10;
    test.lot = 10;

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
    this.transactions.markAllAsTouched();

    // stop here if form is invalid
    if (this.transactions.invalid) {
      alert('FORM INVALID :(');
      return;
    }

    // copy form data to products array on success
    this.products = this.fa.value;
    this.closeAllRows();
    this.isEditMode = false;
    this.transactionDetailsSave.emit(this.transactions);
  }


  public getProduct(id: number): Product {
    return this.productList.find((x) => x.id === id);
  }
  // helper methods
  onCancel() {
    this.closeAllRows();

    // reset products back to original data (before edit was clicked)
    this.products = this.originalProducts;

    this.isEditMode = false;
  }
  public createFormGroup(dataItem: InputDetail = new InputDetail()): FormGroup {
    return this.formBuilder.group({
      productId: [dataItem.productId, Validators.required],
      qte: [dataItem.qte, Validators.required],
      lot: [dataItem.lot, Validators.required],
      article: [dataItem.article, Validators.required]
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
