import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {InputsService} from '../../../../core/services/inputs.service';
import {finalize} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {DatePipe, Location} from '@angular/common';
import {ActorService} from '../../../../core/services/actor.service';

@Component({
  selector: 'app-transfer-create',
  templateUrl: './transfer-create.component.html',
  styleUrls: ['./transfer-create.component.scss']
})

export class TransferCreateComponent implements OnInit {
  saving = false;
  public breadCrumb: BreadCrumb;
  formGroup = this.createFormGroup();
  public actorList;

  constructor(private service: InputsService,
              private actorService: ActorService,
              private formBuilder: FormBuilder,
              private location: Location,
              private matSnackBar: MatSnackBar,
              private datePipe: DatePipe) {
  }

  get form() {
    return this.formGroup.controls;
  }

  get formArrayTransactions(): FormArray {
    return this.formGroup.controls.transactionDetails as FormArray;
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Add Transfer',
      items: [
        {label: 'Transfers', path: '../'},
        {label: 'Create', active: true}
      ]
    };
    this.actorService.getSuppliers().subscribe(data => {
      this.actorList = data;

    });
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      supplierId: [0, Validators.required],
      date: [this.datePipe.transform(Date.now(), 'yyyy-MM-ddThh:mm:ss'), Validators.required],
      description: ['test', Validators.required],
      payement: ['bank', Validators.required],
      payementRef: ['test', Validators.required],
      ref: ['IR', Validators.required],
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
    const fa = transactions.controls.formArray as FormArray;
    this.formArrayTransactions.clear();
    for (let i = 0; i < fa.length; i++) {
      this.formArrayTransactions.push(fa.at(i));
    }
  }
}
