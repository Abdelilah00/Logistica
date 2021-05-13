import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {path: 'inputs', loadChildren: () => import('./inputs/inputs.module').then(m => m.InputsModule)},
  {path: 'outputs', loadChildren: () => import('./outputs/outputs.module').then(m => m.OutputsModule)},
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
