import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SupplierListComponent} from './supplier-list/supplier-list.component';
import {SupplierCreateComponent} from './supplier-create/supplier-create.component';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {path: 'list', component: SupplierListComponent},
  {path: 'edit/:id', component: SupplierCreateComponent},
  {path: 'create', component: SupplierCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [SupplierListComponent, SupplierCreateComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
  ]
})
export class SuppliersModule {
}
