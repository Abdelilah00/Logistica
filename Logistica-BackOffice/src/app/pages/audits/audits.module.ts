import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AuditListComponent} from './audit-list/audit-list.component';
import {AuditCreateComponent} from './audit-create/audit-create.component';
import {ReactiveFormsModule} from '@angular/forms';
import {UiModule} from '../../shared/ui/ui.module';
import {GridModule} from '@progress/kendo-angular-grid';
import {ArchwizardModule} from 'angular-archwizard';


const routes: Routes = [
  {path: 'list', component: AuditListComponent},
  {path: 'edit/:id', component: AuditCreateComponent},
  {path: 'create', component: AuditCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [AuditListComponent, AuditCreateComponent],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    UiModule,
    GridModule,
    ReactiveFormsModule,
    ArchwizardModule,
  ]
})
export class AuditsModule {
}
