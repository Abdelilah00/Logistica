import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AnalyticsComponent} from './analytics/analytics.component';
import {PredictionsComponent} from './predictions/predictions.component';
import {AlertsComponent} from './alerts/alerts.component';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {FormsModule} from '@angular/forms';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {PerfectScrollbarModule} from 'ngx-perfect-scrollbar';
import {WidgetModule} from '../../shared/widget/widget.module';
import {NgApexchartsModule} from 'ng-apexcharts';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {UiModule} from '../../shared/ui/ui.module';


const routes: Routes = [
  {path: 'analytics', component: AnalyticsComponent},
  {path: 'alerts', component: AlertsComponent},
  {path: 'predictions', component: PredictionsComponent},
  {path: '**', redirectTo: 'analytics'},

];


@NgModule({
  declarations: [AlertsComponent, AnalyticsComponent, PredictionsComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    Ng2SearchPipeModule,
    FormsModule,
    LeafletModule,
    PerfectScrollbarModule,
    WidgetModule,
    NgApexchartsModule,
    MatButtonToggleModule,
    UiModule,
  ]
})
export class DashboardsModule {
}
