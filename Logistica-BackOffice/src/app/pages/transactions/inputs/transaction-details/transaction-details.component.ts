import {Component, Input, OnInit} from '@angular/core';
import {finalize} from 'rxjs/operators';
import {TransactionDetail} from '../../../../core/models/auth.models';
import {TransactionDetailsService} from '../../../../core/services/transaction-details.service';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-transaction-details',
  templateUrl: './transaction-details.component.html',
  styleUrls: ['./transaction-details.component.scss']
})

export class TransactionDetailsComponent implements OnInit {
  view: TransactionDetail[];
  @Input() inputId: number | null;
  public editDataItem: TransactionDetail;
  public isNew: boolean;


  constructor(private transactionDetailsService: TransactionDetailsService,
              private matSnackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    console.log(this.inputId);
    if (this.inputId !== null) {
      this.transactionDetailsService.getByInputId(this.inputId).subscribe(data => this.view = data);
    }
  }

  public addHandler(): void {
    const tmp = new TransactionDetail();
    tmp.inputId = this.inputId;
    this.editDataItem = tmp;
    this.isNew = true;
  }

  public editHandler({dataItem}): void {
    this.editDataItem = dataItem;
    this.isNew = false;
  }

  public removeHandler({dataItem}): void {
    this.transactionDetailsService.delete(dataItem.id)
      .pipe(
        finalize(() => {
          this.loadGridData();
        })
      )
      .subscribe(() => {
        // Show the success message
        this.matSnackBar.open('Category Deleted', 'OK', {
          verticalPosition: 'top',
          duration: 2000
        });
      }, (error) => {
        this.matSnackBar.open('Category Not Deleted', 'Try', {
          verticalPosition: 'top',
          duration: 2000
        });
      });
  }

  public cancelHandler(): void {
    this.editDataItem = undefined;
  }

  private loadGridData(): void {
    this.transactionDetailsService.getByInputId(this.inputId).subscribe(data => this.view = data);
  }
}
