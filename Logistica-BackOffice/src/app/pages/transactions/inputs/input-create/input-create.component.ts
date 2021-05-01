import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/auth.models';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {InputService} from '../../../../core/services/input.service';
import {finalize} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-input-create',
  templateUrl: './input-create.component.html',
  styleUrls: ['./input-create.component.scss']
})

export class InputCreateComponent implements OnInit {
  saving = false;
  public breadCrumb: BreadCrumb;
  selectValue = ['Touchscreen', 'Call Function', 'Notifications', 'Fitness', 'Outdoor'];
  formGroup = this.createFormGroup();

  constructor(private service: InputService,
              private formBuilder: FormBuilder,
              private location: Location,
              private matSnackBar: MatSnackBar,
              private datePipe: DatePipe) {
  }

  get formArrayTransactions(): FormArray {
    return this.formGroup.controls.transactionDetails as FormArray;
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Add Input',
      items: [
        {label: 'Inputs', path: '../'},
        {label: 'Create', active: true}
      ]
    };
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      supplierName: ['test', Validators.required],
      date: [this.datePipe.transform(Date.now(), 'yyyy-MM-ddThh:mm:ss'), Validators.required],
      description: ['test', Validators.required],
      payement: ['bank', Validators.required],
      payementRef: ['test', Validators.required],
      transactionDetails: this.formBuilder.array([]),
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
      this.matSnackBar.open('Input saved', 'OK', {
        verticalPosition: 'top',
        duration: 2000
      });
      // this.goBack();
    }, (error) => {
      this.matSnackBar.open('Input Not saved', 'Try', {
        verticalPosition: 'top',
        duration: 2000
      });
    });
  }

  setNewTransactions(transactions: FormGroup): void {
    let fa = transactions.controls.formArray as FormArray;
    for (let i = 0; i < fa.length; i++) {
      this.formArrayTransactions.push(fa.at(i));
    }
  }


}
