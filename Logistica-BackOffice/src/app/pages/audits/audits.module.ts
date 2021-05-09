import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AuditListComponent} from './audit-list/audit-list.component';
import {AuditCreateComponent} from './audit-create/audit-create.component';


const routes: Routes = [
  {path: 'list', component: AuditListComponent},
  {path: 'edit/:id', component: AuditCreateComponent},
  {path: 'create', component: AuditCreateComponent},
  {path: '**', redirectTo: 'list'},
];

@NgModule({
  declarations: [AuditListComponent, AuditCreateComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
  ]
})
export class AuditsModule {
}
