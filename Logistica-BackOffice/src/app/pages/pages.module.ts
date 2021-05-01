import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PERFECT_SCROLLBAR_CONFIG, PerfectScrollbarConfigInterface, PerfectScrollbarModule} from 'ngx-perfect-scrollbar';

import {DashboardComponent} from './dashboard/dashboard.component';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {UiModule} from '../shared/ui/ui.module';
import {WidgetModule} from '../shared/widget/widget.module';
import {NgApexchartsModule} from 'ng-apexcharts';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';


const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true,
  wheelSpeed: 0.3
};

const routes: Routes = [
  {path: '', component: DashboardComponent},
  {path: 'utility', loadChildren: () => import('./utility/utility.module').then(m => m.UtilityModule)},
  {path: 'transactions', loadChildren: () => import('./transactions/transactions.module').then(m => m.TransactionsModule)},
  {path: 'suppliers', loadChildren: () => import('./suppliers/suppliers.module').then(m => m.SuppliersModule)},
  {path: 'products', loadChildren: () => import('./products/products.module').then(m => m.ProductsModule)},
  {path: 'stocks', loadChildren: () => import('./stocks/stocks.module').then(m => m.StocksModule)},
  {path: 'parameters', loadChildren: () => import('./parameters/parameters.module').then(m => m.ParametersModule)},
];

@NgModule({
  declarations: [DashboardComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    Ng2SearchPipeModule,
    ReactiveFormsModule,
    UiModule,
    WidgetModule,
    NgApexchartsModule,
    PerfectScrollbarModule,
    LeafletModule,
    MatSnackBarModule
  ],
  providers: [
    {
      provide: PERFECT_SCROLLBAR_CONFIG,
      useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG,
    },
    MatSnackBar
  ],
  exports: [RouterModule]
})
export class PagesModule {
}
