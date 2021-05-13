import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {TaskCreateComponent} from './task-create/task-create.component';
import {TaskListComponent} from './task-list/task-list.component';
import {ReactiveFormsModule} from '@angular/forms';
import {UiModule} from '../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ArchwizardModule} from 'angular-archwizard';


const routes: Routes = [
  {path: 'list', component: TaskListComponent},
  {path: 'edit/:id', component: TaskCreateComponent},
  {path: 'create', component: TaskCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [TaskListComponent, TaskCreateComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule,
    ArchwizardModule
  ]
})
export class TasksModule {
}
