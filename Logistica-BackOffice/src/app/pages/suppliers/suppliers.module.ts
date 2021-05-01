import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SupplierListComponent} from './supplier-list/supplier-list.component';
import {SupplierCreateComponent} from './supplier-create/supplier-create.component';
import {RouterModule, Routes} from '@angular/router';
import {UiModule} from '../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ReactiveFormsModule} from '@angular/forms';
import {SupplierEditComponent} from './supplier-edit/supplier-edit.component';


const routes: Routes = [
  {path: 'list', component: SupplierListComponent},
  {path: 'edit/:id', component: SupplierEditComponent},
  {path: 'create', component: SupplierCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [SupplierListComponent, SupplierCreateComponent, SupplierEditComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule,
  ]
})
export class SuppliersModule {
}
