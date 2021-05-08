import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/auth.models';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {finalize} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {DatePipe, Location} from '@angular/common';
import {OutputsService} from '../../../../core/services/outputs.service';

@Component({
  selector: 'app-input-create',
  templateUrl: './output-create.component.html',
  styleUrls: ['./output-create.component.scss']
})

export class OutputCreateComponent implements OnInit {
  saving = false;
  public breadCrumb: BreadCrumb;
  selectValue = ['Touchscreen', 'Call Function', 'Notifications', 'Fitness', 'Outdoor'];
  formGroup = this.createFormGroup();

  constructor(private service: OutputsService,
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
      title: 'Create Output',
      items: [
        {label: 'Outputs', path: '../'},
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
