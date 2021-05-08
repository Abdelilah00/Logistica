import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UiModule} from '../../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ReactiveFormsModule} from '@angular/forms';
import {StockRespoCreateComponent} from './stock-respo-create/stock-respo-create.component';
import {StockRespoListComponent} from './stock-respo-list/stock-respo-list.component';


const routes: Routes = [
  {path: 'list', component: StockRespoListComponent},
  {path: 'edit/:id', component: StockRespoCreateComponent},
  {path: 'create', component: StockRespoCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [StockRespoCreateComponent, StockRespoListComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule
  ]
})
export class StockRespoModule {
}
