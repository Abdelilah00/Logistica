import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {path: 'input', loadChildren: () => import('./input/input.module').then(m => m.InputModule)},
  {path: 'output', loadChildren: () => import('./output/output.module').then(m => m.OutputModule)},
  {path: 'transfer', loadChildren: () => import('./transfer/transfer.module').then(m => m.TransferModule)},
  {path: '**', redirectTo: 'inputs'}
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
})
export class TransactionsModule {
}
