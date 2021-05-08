import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {InputListComponent} from './input-list/input-list.component';
import {InputCreateComponent} from './input-create/input-create.component';
import {RouterModule, Routes} from '@angular/router';
import {TransactionDetailsComponent} from './transaction-details/transaction-details.component';
import {TransactionDetailsCreateComponent} from './transaction-details-create/transaction-details-create.component';
import {UiModule} from '../../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ReactiveFormsModule} from '@angular/forms';
import {ArchwizardModule} from 'angular-archwizard';

const routes: Routes = [
  {path: 'list', component: InputListComponent},
  {path: 'edit/:id', component: InputCreateComponent},
  {path: 'create', component: InputCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [
    InputListComponent,
    InputCreateComponent,
    TransactionDetailsComponent,
    TransactionDetailsCreateComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule,
    ArchwizardModule
  ],
  providers: [
    DatePipe
  ]
})
export class InputsModule {
}
