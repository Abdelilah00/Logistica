import {Component, OnInit} from '@angular/core';
import {BreadCrumb, Category} from '../../../../core/models/all.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductsService} from '../../../../core/services/Products/products.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {CategoryService} from '../../../../core/services/Products/category.service';
import {finalize} from 'rxjs/operators';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.scss']
})
export class ProductEditComponent implements OnInit {


  formGroup = this.createFormGroup();
  saving = false;
  public breadCrumb: BreadCrumb;
  categoryList: Category[];

  constructor(private formBuilder: FormBuilder,
              private service: ProductsService,
              private matSnackBar: MatSnackBar,
              private location: Location,
              private categoryService: CategoryService,
              private route: ActivatedRoute
  ) {
  }

  get form() {
    return this.formGroup.controls;
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Add Supplier',
      items: [
        {label: 'Products', path: '../'},
        {label: 'Create', active: true}
      ]
    };
    this.categoryService.getAll().subscribe(data => {
      this.categoryList = data;

      this.service.get(this.route.snapshot.params['id']).subscribe(data1 => {
        const categoryId: number = this.categoryList.find(item => item.name === data1.categoryName).id;
        this.formGroup.reset({...data1, categoryId: categoryId});
      });
    });

  }


  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      id: [0, Validators.required],
      name: ['', Validators.required],
      stockMin: [0, Validators.required],
      stockMax: [0, Validators.required],
      stockSecurity: [0, Validators.required],
      priceHT: [0.0, Validators.required],
      tva: [0, Validators.required],
      categoryId: [0, Validators.required],
    });
  }

  submit(): void {
    this.saving = true;
    this.service.update({
      ...this.formGroup.value,
      categoryId: parseInt(this.formGroup.value.categoryId),
      tva: parseInt(this.formGroup.value.tva)
    })
      .pipe(
        finalize(() => {
          this.saving = false;
        })
      ).subscribe(() => {
      // Show the success message
      this.matSnackBar.open('Product updated', 'Ok', {
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
