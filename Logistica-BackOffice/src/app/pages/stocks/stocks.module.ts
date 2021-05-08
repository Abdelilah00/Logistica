import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {path: 'stock', loadChildren: () => import('./stock/stock.module').then(m => m.StockModule)},
  {path: 'stock-respo', loadChildren: () => import('./stock-respo/stock-respo.module').then(m => m.StockRespoModule)},
  {path: '**', redirectTo: 'stock'}
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
