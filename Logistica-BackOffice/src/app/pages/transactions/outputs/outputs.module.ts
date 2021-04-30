import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {GridModule} from '@progress/kendo-angular-grid';
import {UiModule} from '../../../shared/ui/ui.module';
import {ArchwizardModule} from 'angular-archwizard';
import {NgSelectModule} from '@ng-select/ng-select';
import {DropzoneModule} from 'ngx-dropzone-wrapper';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ExcelExportModule} from '@progress/kendo-angular-excel-export';
import {InputListComponent} from './input-list/input-list.component';
import {InputCreateComponent} from './input-create/input-create.component';
import {TransactionDetailsComponent} from './transaction-details/transaction-details.component';
import {TransactionDetailsCreateComponent} from './transaction-details-create/transaction-details-create.component';


const routes: Routes = [
  {path: 'list', component: InputListComponent},
  {path: 'edit/:id', component: InputCreateComponent},
  {path: 'create', component: InputCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [InputListComponent, InputCreateComponent, TransactionDetailsComponent, TransactionDetailsCreateComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    GridModule,
    UiModule,
    ArchwizardModule,
    NgSelectModule,
    DropzoneModule,
    ReactiveFormsModule,
    FormsModule,
    ExcelExportModule,
  ],
  providers: [
    DatePipe
  ]
})
export class OutputsModule {
}
