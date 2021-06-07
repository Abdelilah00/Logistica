import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductsService} from '../../../../core/services/Products/products.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.component.html',
  styleUrls: ['./category-create.component.scss']
})
export class CategoryCreateComponent implements OnInit {


  formGroup = this.createFormGroup();
  saving = false;
  public breadCrumb: BreadCrumb;

  constructor(private formBuilder: FormBuilder,
              private service: ProductsService,
              private matSnackBar: MatSnackBar,
              private location: Location,
  ) {
  }

  get form() {
    return this.formGroup.controls;
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Create Category',
      items: [
        {label: 'Categories', path: '../'},
        {label: 'Create', active: true}
      ]
    };
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: ['category Test', Validators.required],
      parentId: [null, Validators.required],

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
      this.matSnackBar.open('Category saved', 'Ok', {
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
