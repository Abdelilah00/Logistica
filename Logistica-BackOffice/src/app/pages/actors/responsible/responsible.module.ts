import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ResponsibleListComponent} from './responsible-list/responsible-list.component';
import {ResponsibleCreateComponent} from './responsible-create/responsible-create.component';
import {ReactiveFormsModule} from '@angular/forms';
import {UiModule} from '../../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';

const routes: Routes = [
  {path: 'list', component: ResponsibleListComponent},
  {path: 'edit/:id', component: ResponsibleCreateComponent},
  {path: 'create', component: ResponsibleCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [ResponsibleListComponent, ResponsibleCreateComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    GridModule,
    ReactiveFormsModule,
    UiModule
  ]
})
export class ResponsibleModule {
}
