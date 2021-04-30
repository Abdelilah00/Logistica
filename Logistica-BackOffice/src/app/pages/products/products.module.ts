import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductListComponent} from './product-list/product-list.component';
import {RouterModule, Routes} from '@angular/router';
import {SupplierCreateComponent} from '../suppliers/supplier-create/supplier-create.component';
import {ProductCreateComponent} from './product-create/product-create.component';
import {ProductEditComponent} from './product-edit/product-edit.component';


const routes: Routes = [
  {path: 'list', component: ProductListComponent},
  {path: 'edit/:id', component: ProductEditComponent},
  {path: 'create', component: SupplierCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [ProductListComponent, ProductCreateComponent, ProductEditComponent],
  imports: [RouterModule.forChild(routes),

    CommonModule
  ]
})
export class ProductsModule {
}
