import {Component, OnInit} from '@angular/core';
import {BreadCrumb, Category} from '../../../../core/models/all.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {finalize} from 'rxjs/operators';
import {ProductsService} from '../../../../core/services/Products/products.service';
import {CategoryService} from '../../../../core/services/Products/category.service';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.scss']
})
export class ProductCreateComponent implements OnInit {

  formGroup = this.createFormGroup();
  saving = false;
  public breadCrumb: BreadCrumb;
  categoryList: Category[];

  constructor(private formBuilder: FormBuilder,
              private service: ProductsService,
              private matSnackBar: MatSnackBar,
              private location: Location,
              private categoryService: CategoryService
  ) {
  }

  get form() {
    return this.formGroup.controls;
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Add Supplier',
      items: [
        {label: 'Suppliers', path: '../'},
        {label: 'Create', active: true}
      ]
    };
    this.categoryService.getAll().subscribe(data => this.categoryList = data);
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: ['test', Validators.required],
      stockMin: [100, Validators.required],
      stockMax: [1000, Validators.required],
      stockSecurity: [250, Validators.required],
      priceHT: [100.5, Validators.required],
      tva: [20, Validators.required],
      categoryId: [1, Validators.required],
    });
  }

  submit(): void {
    this.saving = true;
    this.service.create(this.formGroup.value)
      .pipe(
        finalize(() => {
          this.saving = false;
        })
      ).subscribe(() => {
      // Show the success message
      this.matSnackBar.open('Product saved', 'Ok', {
        verticalPosition: 'top',
        duration: 3000,
        panelClass: ['green-snackbar']
      });
      this.goBack();
    });
  }

  goBack(): void {
    this.location.back();
  }
}
