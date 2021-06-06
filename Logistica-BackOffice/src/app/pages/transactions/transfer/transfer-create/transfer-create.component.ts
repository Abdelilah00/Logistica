import {Component, OnInit} from '@angular/core';
import {BreadCrumb} from '../../../../core/models/all.models';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {finalize} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {DatePipe, Location} from '@angular/common';
import {ActorService} from '../../../../core/services/Commands/actor.service';
import {StocksService} from '../../../../core/services/Products/stocks.service';
import {StructureUnitsService} from '../../../../core/services/Organzation/structure-units.service';
import {TransfersService} from '../../../../core/services/Products/transfers.service';

@Component({
  selector: 'app-transfer-create',
  templateUrl: './transfer-create.component.html',
  styleUrls: ['./transfer-create.component.scss']
})

export class TransferCreateComponent implements OnInit {
  saving = false;
  public breadCrumb: BreadCrumb;
  formGroup = this.createFormGroup();
  public respoList;
  public stockList;
  public serviceList;

  constructor(private service: TransfersService,
              private actorService: ActorService,
              private stocksService: StocksService,
              private servicesService: StructureUnitsService,
              private formBuilder: FormBuilder,
              private location: Location,
              private matSnackBar: MatSnackBar,
              private datePipe: DatePipe) {
  }

  get form() {
    return this.formGroup.controls;
  }

  get formArrayTransactions(): FormArray {
    return this.formGroup.controls.transferDetails as FormArray;
  }

  ngOnInit(): void {
    this.breadCrumb = {
      title: 'Add Transfer',
      items: [
        {label: 'Transfers', path: '../'},
        {label: 'Create', active: true}
      ]
    };
    this.actorService.getResponsible().subscribe(data => {
      this.respoList = data;
    });

    this.stocksService.getAll().subscribe(data => {
      this.stockList = data;
    });

    this.servicesService.getAll().subscribe(data => {
      this.serviceList = data;
    });
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      ref: ['Trans00001', Validators.required],
      description: ['desc test', Validators.required],
      delay: [10, Validators.required],
      actorId: [1, Validators.required],
      fromStructureUnitId: [1, Validators.required],
      toStructureUnitId: [1, Validators.required],
      toStockId: [1, Validators.required],
      fromStockId: [1, Validators.required],
      transferDetails: this.formBuilder.array([])
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
      this.matSnackBar.open('Transfer saved', 'Ok', {
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
