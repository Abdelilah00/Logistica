import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {finalize} from 'rxjs/operators';
import {ProductsService} from '../../../../core/services/Products/products.service';

@Component({
  selector: 'app-unite-create',
  templateUrl: './unite-create.component.html',
  styleUrls: ['./unite-create.component.scss']
})
export class UniteCreateComponent implements OnInit {

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
      title: 'Add Supplier',
      items: [
        {label: 'Suppliers', path: '../'},
        {label: 'Create', active: true}
      ]
    };
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: ['test', Validators.required],
      stockMin: [100, Validators.required],
      stockMax: [1000, Validators.required],
      stockSecurity: [250, Validators.required],
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
      this.matSnackBar.open('Product saved', 'OK', {
        verticalPosition: 'top',
        duration: 2000,
      });
      this.goBack();
    }, (error) => {
      this.matSnackBar.open('Product Not saved', 'Try', {
        verticalPosition: 'top',
        duration: 2000
      });
    });
  }

  goBack(): void {
    this.location.back();
  }
}
