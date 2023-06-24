import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {StatComponent} from './stat/stat.component';

import {NgbPaginationModule, NgbTypeaheadModule} from '@ng-bootstrap/ng-bootstrap';
import {LoadingComponent} from './loading/loading.component';

@NgModule({
  declarations: [StatComponent, LoadingComponent],
  imports: [
    CommonModule,
    FormsModule,
    NgbTypeaheadModule,
    NgbPaginationModule
  ],
  exports: [StatComponent, LoadingComponent]
})
export class WidgetModule {
}
