import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StockListComponent} from './stock-list/stock-list.component';
import {StockCreateComponent} from './stock-create/stock-create.component';
import {RouterModule, Routes} from '@angular/router';
import {UiModule} from '../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ReactiveFormsModule} from '@angular/forms';


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
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule
  ]
})
export class StocksModule {
}
