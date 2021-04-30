import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StockListComponent} from './stock-list/stock-list.component';
import {StockCreateComponent} from './stock-create/stock-create.component';
import {RouterModule, Routes} from '@angular/router';
import {SupplierListComponent} from '../suppliers/supplier-list/supplier-list.component';
import {SupplierCreateComponent} from '../suppliers/supplier-create/supplier-create.component';


const routes: Routes = [
  {path: 'list', component: SupplierListComponent},
  {path: 'edit/:id', component: SupplierCreateComponent},
  {path: 'create', component: SupplierCreateComponent},
  {path: '**', redirectTo: 'list'},
];


@NgModule({
  declarations: [StockListComponent, StockCreateComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ]
})
export class StocksModule {
}
