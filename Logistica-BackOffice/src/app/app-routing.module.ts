import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {LayoutComponent} from './layouts/layout/layout.component';
import {Page404Component} from './extrapages/page404/page404.component';
import {AuthGuard} from './core/auth.guard';

const routes: Routes = [
  {path: 'account', loadChildren: () => import('./account/account.module').then(m => m.AccountModule)},
  {
    path: '',
    component: LayoutComponent,
    loadChildren: () => import('./pages/pages.module').then(m => m.PagesModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'extrapages',
    loadChildren: () => import('./extrapages/extrapages.module').then(m => m.ExtrapagesModule),
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    component: Page404Component,
    pathMatch: 'prefix'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {scrollPositionRestoration: 'top'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
