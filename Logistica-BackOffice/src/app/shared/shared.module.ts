import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UiModule} from './ui/ui.module';
import { LoadingComponent } from './widget/loading/loading.component';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    UiModule
  ]
})
export class SharedModule {
}
