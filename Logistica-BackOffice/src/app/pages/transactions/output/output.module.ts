import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {OutputListComponent} from './output-list/output-list.component';
import {OutputCreateComponent} from './output-create/output-create.component';
import {OutputDetailsComponent} from './output-details/output-details.component';
import {OutputDetailsCreateComponent} from './output-details-create/output-details-create.component';
import {UiModule} from '../../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ReactiveFormsModule} from '@angular/forms';
import {ArchwizardModule} from 'angular-archwizard';
import {DropDownListModule} from '@progress/kendo-angular-dropdowns';


const routes: Routes = [
  {path: 'list', component: OutputListComponent},
  {path: 'edit/:id', component: OutputCreateComponent},
  {path: 'create', component: OutputCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [OutputListComponent, OutputCreateComponent, OutputDetailsComponent, OutputDetailsCreateComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule,
    ArchwizardModule,
    DropDownListModule,
  ],
  providers: [
    DatePipe
  ]
})
export class OutputModule {
}
