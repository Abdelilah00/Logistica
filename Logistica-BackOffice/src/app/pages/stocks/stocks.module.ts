import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StockListComponent} from './stock-list/stock-list.component';
import {StockCreateComponent} from './stock-create/stock-create.component';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {path: 'list', component: StockListComponent},
  {path: 'edit/:id', component: StockCreateComponent},
  {path: 'create', component: StockCreateComponent},
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
