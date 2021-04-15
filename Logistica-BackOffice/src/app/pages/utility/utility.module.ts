import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {NgbAccordionModule, NgbNavModule} from '@ng-bootstrap/ng-bootstrap';

import {UtilityRoutingModule} from './utility-routing.module';
import {UiModule} from '../../shared/ui/ui.module';

import {StarterComponent} from './starter/starter.component';
import {TimelineComponent} from './timeline/timeline.component';
import {FaqsComponent} from './faqs/faqs.component';

@NgModule({
  declarations: [StarterComponent, TimelineComponent, FaqsComponent],
  imports: [
    CommonModule,
    UtilityRoutingModule,
    UiModule,
    NgbAccordionModule,
    NgbNavModule
  ]
})
export class UtilityModule {
}
