import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  /*  {path: 'list', component: SupplierListComponent},
    {path: '**', redirectTo: 'list'},*/
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes),

    CommonModule
  ]
})
export class ParametersModule {
}
