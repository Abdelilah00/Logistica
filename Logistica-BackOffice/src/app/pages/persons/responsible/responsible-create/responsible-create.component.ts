import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BreadCrumb} from '../../../../core/models/all.models';
import {finalize} from 'rxjs/operators';
import {ActorService} from '../../../../core/services/actor.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';

@Component({
  selector: 'app-supplier-create',
  templateUrl: './responsible-create.component.html',
  styleUrls: ['./responsible-create.component.scss']
})
export class ResponsibleCreateComponent implements OnInit {
  formGroup = this.createFormGroup();
  saving = false;
  public breadCrumb: BreadCrumb;

  constructor(private formBuilder: FormBuilder,
              private service: ActorService,
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
      adresse: ['test', Validators.required],
      nRCommerce: ['bank', Validators.required],
      contactPhone: ['test', Validators.required],
      contactWebSite: ['test', Validators.required],
      contactEmail: ['test', Validators.required],
      sectorName: ['test', Validators.required],
      bankName: ['test', Validators.required],
      bankCode: ['test', Validators.required],
      bankAccountNumber: ['test', Validators.required],
      bankRIB: ['test', Validators.required],
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
      this.matSnackBar.open('Supplier saved', 'OK', {
        verticalPosition: 'top',
        duration: 2000,
      });
      this.goBack();
    }, (error) => {
      this.matSnackBar.open('Supplier Not saved', 'Try', {
        verticalPosition: 'top',
        duration: 2000
      });
    });
  }

  goBack(): void {
    this.location.back();
  }
}
