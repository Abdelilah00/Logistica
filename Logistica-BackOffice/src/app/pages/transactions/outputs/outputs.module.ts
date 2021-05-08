import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {OutputListComponent} from './output-list/output-list.component';
import {OutputCreateComponent} from './output-create/output-create.component';
import {TransactionDetailsComponent} from './transaction-details/transaction-details.component';
import {TransactionDetailsCreateComponent} from './transaction-details-create/transaction-details-create.component';
import {UiModule} from '../../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ReactiveFormsModule} from '@angular/forms';
import {ArchwizardModule} from 'angular-archwizard';


const routes: Routes = [
  {path: 'list', component: OutputListComponent},
  {path: 'edit/:id', component: OutputCreateComponent},
  {path: 'create', component: OutputCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [OutputListComponent, OutputCreateComponent, TransactionDetailsComponent, TransactionDetailsCreateComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule,
    ArchwizardModule,

  ],
  providers: [
    DatePipe
  ]
})
export class OutputsModule {
}
