import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {DashboardComponent} from './dashboard/dashboard.component';

const routes: Routes = [
  {path: '', component: DashboardComponent},
  {path: 'utility', loadChildren: () => import('./utility/utility.module').then(m => m.UtilityModule)},
  {path: 'transactions', loadChildren: () => import('./transactions/transactions.module').then(m => m.TransactionsModule)},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule {
}
