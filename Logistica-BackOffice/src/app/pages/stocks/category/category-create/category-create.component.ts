import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {finalize} from 'rxjs/operators';
import {ActivatedRoute} from '@angular/router';
import {CategoryService} from '../../../../core/services/Products/category.service';

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
              private service: CategoryService,
              private matSnackBar: MatSnackBar,
              private location: Location,
              private route: ActivatedRoute,
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
    const parentId = this.route.snapshot.queryParamMap.get('parentId');
    return this.formBuilder.group({
      name: ['category ', Validators.required],
      defaultTva: [20.0, Validators.required],
      defaultStockMin: [200, Validators.required],
      defaultStockMax: [2500, Validators.required],
      defaultStockSecurity: [1000, Validators.required],
      parentId: [parentId, Validators.required],
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
