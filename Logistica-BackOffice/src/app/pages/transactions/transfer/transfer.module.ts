import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {TransferListComponent} from './transfer-list/transfer-list.component';
import {TransferCreateComponent} from './transfer-create/transfer-create.component';
import {RouterModule, Routes} from '@angular/router';
import {TransactionDetailsComponent} from './transfer-details/transaction-details.component';
import {TransactionDetailsCreateComponent} from './transfer-details-create/transaction-details-create.component';
import {UiModule} from '../../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ReactiveFormsModule} from '@angular/forms';
import {ArchwizardModule} from 'angular-archwizard';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatSelectFilterModule} from 'mat-select-filter';
import {DropDownListModule} from '@progress/kendo-angular-dropdowns';
import {PopupModule} from '@progress/kendo-angular-popup';

const routes: Routes = [
  {path: 'list', component: TransferListComponent},
  {path: 'edit/:id', component: TransferCreateComponent},
  {path: 'create', component: TransferCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [
    TransferListComponent,
    TransferCreateComponent,
    TransactionDetailsComponent,
    TransactionDetailsCreateComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule,
    ArchwizardModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatInputModule,
    MatSelectFilterModule,
    DropDownListModule,
    PopupModule
  ],
  providers: [
    DatePipe
  ]
})
export class TransferModule {
}
