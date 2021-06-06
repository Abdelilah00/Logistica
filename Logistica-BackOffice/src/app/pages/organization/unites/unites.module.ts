import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UniteCreateComponent} from './unite-create/unite-create.component';
import {UniteEditComponent} from './unite-edit/unite-edit.component';
import {UniteListComponent} from './unite-list/unite-list.component';
import {UniteDetailsComponent} from './unite-details/unite-details.component';
import {GridModule} from '@progress/kendo-angular-grid';
import {UiModule} from '../../../shared/ui/ui.module';
import {RouterModule, Routes} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';


const routes: Routes = [
  {path: 'list', component: UniteListComponent},
  {path: 'edit/:id', component: UniteEditComponent},
  {path: 'create', component: UniteCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [UniteCreateComponent, UniteEditComponent, UniteListComponent, UniteDetailsComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    GridModule,
    UiModule,
    RouterModule,
    ReactiveFormsModule
  ]
})
export class UnitesModule {
}
