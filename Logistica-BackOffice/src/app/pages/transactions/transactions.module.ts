import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {GridModule} from '@progress/kendo-angular-grid';
import {MatSnackBarModule} from '@angular/material/snack-bar';

const routes: Routes = [
  {path: 'inputs', loadChildren: () => import('./inputs/inputs.module').then(m => m.InputsModule)},
  {path: '**', redirectTo: 'inputs'}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes),
    CommonModule, GridModule, MatSnackBarModule
  ],
})
export class TransactionsModule {
}
