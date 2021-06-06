import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {finalize} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {DatePipe, Location} from '@angular/common';
import {OutputsService} from '../../../../core/services/Products/outputs.service';
import {ActorService} from '../../../../core/services/Commands/actor.service';

@Component({
  selector: 'app-input-create',
  templateUrl: './output-create.component.html',
  styleUrls: ['./output-create.component.scss']
})

export class OutputCreateComponent implements OnInit {
  saving = false;
  public breadCrumb: BreadCrumb;
  formGroup = this.createFormGroup();
  public clientsList;

  constructor(private service: OutputsService,
              private formBuilder: FormBuilder,
              private matSnackBar: MatSnackBar,
              private datePipe: DatePipe,
              private location: Location,
              private actorService: ActorService,
  ) {
  }

  get formArrayTransactions(): FormArray {
    return this.formGroup.controls.transactionDetails as FormArray;
  }

  get form() {
    return this.formGroup.controls;
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Create Output',
      items: [
        {label: 'Outputs', path: '../'},
        {label: 'Create', active: true}
      ]
    };
    this.actorService.getClients().subscribe(data => {
      this.clientsList = data;
    });
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      actorId: [0, Validators.required],
      date: [this.datePipe.transform(Date.now(), 'yyyy-MM-ddThh:mm:ss'), Validators.required],
      description: ['test', Validators.required],
      payement: ['bank', Validators.required],
      payementRef: ['test', Validators.required],
      ref: ['OR', Validators.required],
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
      this.matSnackBar.open('Output saved', 'Ok', {
        verticalPosition: 'top',
        duration: 3000,
        panelClass: ['green-snackbar']
      });
      this.goBack();
    });
  }

  setNewTransactions(transactions: FormGroup): void {
    const fa = transactions.controls.formArray as FormArray;
    this.formArrayTransactions.clear();
    for (let i = 0; i < fa.length; i++) {
      this.formArrayTransactions.push(fa.at(i));
    }
  }

  goBack(): void {
    this.location.back();
  }
}
