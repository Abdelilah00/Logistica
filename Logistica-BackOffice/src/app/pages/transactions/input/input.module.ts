import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {InputListComponent} from './input-list/input-list.component';
import {InputCreateComponent} from './input-create/input-create.component';
import {RouterModule, Routes} from '@angular/router';
import {InputDetailsComponent} from './input-details/input-details.component';
import {InputDetailsCreateComponent} from './input-details-create/input-details-create.component';
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
  {path: 'list', component: InputListComponent},
  {path: 'edit/:id', component: InputCreateComponent},
  {path: 'create', component: InputCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [
    InputListComponent,
    InputCreateComponent,
    InputDetailsComponent,
    InputDetailsCreateComponent
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
export class InputModule {
}
