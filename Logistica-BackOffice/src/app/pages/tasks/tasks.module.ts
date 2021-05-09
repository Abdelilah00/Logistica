import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {TaskCreateComponent} from './task-create/task-create.component';
import {TaskListComponent} from './task-list/task-list.component';


const routes: Routes = [
  {path: 'list', component: TaskListComponent},
  {path: 'edit/:id', component: TaskCreateComponent},
  {path: 'create', component: TaskCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [TaskListComponent, TaskCreateComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
  ]
})
export class TasksModule {
}
