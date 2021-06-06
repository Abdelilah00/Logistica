import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StructureCreateComponent} from './structure-create/structure-create.component';
import {StructureEditComponent} from './structure-edit/structure-edit.component';
import {StructureListComponent} from './structure-list/structure-list.component';
import {StructureDetailsComponent} from './structure-details/structure-details.component';
import {UiModule} from '../../../shared/ui/ui.module';
import {ReactiveFormsModule} from '@angular/forms';
import {GridModule} from '@progress/kendo-angular-grid';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: 'list', component: StructureListComponent},
  {path: 'edit/:id', component: StructureEditComponent},
  {path: 'create', component: StructureCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [StructureCreateComponent, StructureDetailsComponent, StructureListComponent, StructureEditComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    ReactiveFormsModule,
    GridModule,
    RouterModule
  ]
})
export class StructuresModule {
}
