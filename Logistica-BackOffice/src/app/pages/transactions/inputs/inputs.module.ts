import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {InputListComponent} from './input-list/input-list.component';
import {InputCreateComponent} from './input-create/input-create.component';
import {RouterModule, Routes} from '@angular/router';
import {ArchwizardModule} from 'angular-archwizard';
import {UiModule} from '../../../shared/ui/ui.module';
import {NgSelectModule} from '@ng-select/ng-select';
import {DropzoneModule} from 'ngx-dropzone-wrapper';
import {GridModule, SharedModule} from '@progress/kendo-angular-grid';
import {TransactionDetailsComponent} from './transaction-details/transaction-details.component';


const routes: Routes = [
  {path: 'list', component: InputListComponent},
  {path: 'edit/:id', component: InputCreateComponent},
  {path: 'create', component: InputCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [InputListComponent, InputCreateComponent, TransactionDetailsComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    ArchwizardModule,
    UiModule,
    NgSelectModule,
    DropzoneModule,
    GridModule,
    SharedModule,
  ]
})
export class InputsModule {
}
