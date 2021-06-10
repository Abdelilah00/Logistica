import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PERFECT_SCROLLBAR_CONFIG, PerfectScrollbarConfigInterface, PerfectScrollbarModule} from 'ngx-perfect-scrollbar';

import {RouterModule, Routes} from '@angular/router';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {UiModule} from '../shared/ui/ui.module';
import {WidgetModule} from '../shared/widget/widget.module';
import {NgApexchartsModule} from 'ng-apexcharts';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';
import {MatButtonToggleModule} from '@angular/material/button-toggle';


const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true,
  wheelSpeed: 0.3
};

const routes: Routes = [
  {path: 'dashboards', loadChildren: () => import('./dashboards/dashboards.module').then(m => m.DashboardsModule)},
  {path: 'actors', loadChildren: () => import('./actors/actors.module').then(m => m.ActorsModule)},
  {path: 'organization', loadChildren: () => import('./organization/organization.module').then(m => m.OrganizationModule)},
  {path: 'stocks', loadChildren: () => import('./stocks/stocks.module').then(m => m.StocksModule)},
  {path: 'transactions', loadChildren: () => import('./transactions/transactions.module').then(m => m.TransactionsModule)},
  {path: 'tasks', loadChildren: () => import('./tasks/tasks.module').then(m => m.TasksModule)},
  {path: 'audits', loadChildren: () => import('./audits/audits.module').then(m => m.AuditsModule)},
  {path: 'utility', loadChildren: () => import('./utility/utility.module').then(m => m.UtilityModule)},
  {path: 'parameters', loadChildren: () => import('./parameters/parameters.module').then(m => m.ParametersModule)},
  {path: '**', loadChildren: () => import('./dashboards/dashboards.module').then(m => m.DashboardsModule)},
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    Ng2SearchPipeModule,
    UiModule,
    WidgetModule,
    NgApexchartsModule,
    PerfectScrollbarModule,
    LeafletModule,
    MatSnackBarModule,
    FormsModule,
    ReactiveFormsModule,
    NgbDropdownModule,
    MatButtonToggleModule
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
