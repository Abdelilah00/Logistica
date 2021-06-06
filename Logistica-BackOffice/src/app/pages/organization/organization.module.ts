import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {path: 'structures', loadChildren: () => import('./structures/structures.module').then(m => m.StructuresModule)},
  {path: 'unites', loadChildren: () => import('./unites/unites.module').then(m => m.UnitesModule)},
  {path: '**', redirectTo: 'structures'}
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ]
})
export class OrganizationModule {
}
