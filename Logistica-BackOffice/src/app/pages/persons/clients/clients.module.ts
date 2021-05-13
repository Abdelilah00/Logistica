import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ClientCreateComponent} from './client-create/client-create.component';
import {ClientListComponent} from './client-list/client-list.component';
import {RouterModule, Routes} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {UiModule} from '../../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';

const routes: Routes = [
  {path: 'list', component: ClientListComponent},
  {path: 'edit/:id', component: ClientCreateComponent},
  {path: 'create', component: ClientCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [ClientListComponent, ClientCreateComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    GridModule,
    ReactiveFormsModule,
    UiModule
  ]
})
export class ClientsModule {
}
