import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {path: 'product', loadChildren: () => import('./product/product.module').then(m => m.ProductModule)},
  {path: 'stock', loadChildren: () => import('./stock/stock.module').then(m => m.StockModule)},
  {path: 'category', loadChildren: () => import('./category/category.module').then(m => m.CategoryModule)},
  {path: '**', redirectTo: 'product'}
];


@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ]
})
export class StocksModule {
}
