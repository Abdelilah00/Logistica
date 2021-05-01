import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../core/models/auth.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductsService} from '../../../core/services/products.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-stock-create',
  templateUrl: './stock-create.component.html',
  styleUrls: ['./stock-create.component.scss']
})
export class StockCreateComponent implements OnInit {


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
      title: 'Create Stock',
      items: [
        {label: 'Stocks', path: '../'},
        {label: 'Create', active: true}
      ]
    };
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: ['test', Validators.required],
      stockSecurity: [250, Validators.required],
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
      this.matSnackBar.open('Stock saved', 'OK', {
        verticalPosition: 'top',
        duration: 2000,
      });
      this.goBack();
    }, (error) => {
      this.matSnackBar.open('Stock Not saved', 'Try', {
        verticalPosition: 'top',
        duration: 2000
      });
    });
  }

  goBack(): void {
    this.location.back();
  }

}
