import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {CategoryListComponent} from './category-list/category-list.component';
import {CategoryCreateComponent} from './category-create/category-create.component';
import {UiModule} from '../../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ReactiveFormsModule} from '@angular/forms';
import {CategoryDetailsComponent} from './category-details/category-details.component';
import {MatIconModule} from '@angular/material/icon';


const routes: Routes = [
  {path: 'list', component: CategoryListComponent},
  {path: 'edit/:id', component: CategoryCreateComponent},
  {path: 'create', component: CategoryCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [CategoryListComponent, CategoryCreateComponent, CategoryDetailsComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule,
    MatIconModule
  ]
})
export class CategoryModule {
}
