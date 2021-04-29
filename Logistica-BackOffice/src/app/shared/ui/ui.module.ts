import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PagetitleComponent} from './pagetitle/pagetitle.component';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [PagetitleComponent],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [PagetitleComponent]
})
export class UiModule {
}
