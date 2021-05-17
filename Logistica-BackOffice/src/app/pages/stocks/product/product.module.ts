import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductListComponent} from './product-list/product-list.component';
import {RouterModule, Routes} from '@angular/router';
import {ProductCreateComponent} from './product-create/product-create.component';
import {ProductEditComponent} from './product-edit/product-edit.component';
import {UiModule} from '../../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ReactiveFormsModule} from '@angular/forms';


const routes: Routes = [
  {path: 'list', component: ProductListComponent},
  {path: 'edit/:id', component: ProductEditComponent},
  {path: 'create', component: ProductCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [ProductListComponent, ProductCreateComponent, ProductEditComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule
  ]
})
export class ProductModule {
}
