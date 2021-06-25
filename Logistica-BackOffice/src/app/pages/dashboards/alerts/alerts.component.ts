import {Component, OnInit} from '@angular/core';
import {AlertsItem} from '../../../core/models/dashboard.model';
import {DashboardAlertsService} from '../../../core/services/Dashboards/dashboard-alerts.service';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.scss']
})
export class AlertsComponent implements OnInit {
  realAlerts: AlertsItem[];
  predAlerts: AlertsItem[];

  constructor(private service: DashboardAlertsService) {
  }

  ngOnInit(): void {
    this.service.getRealQte([]).subscribe(data => {
      data.sort((a, b) => (a.name > b.name ? 1 : -1));
      this.realAlerts = data;
    });
    this.service.getPredQte(['forecast=10']).subscribe(data => {
      data.sort((a, b) => (a.name > b.name ? 1 : -1));
      this.predAlerts = data;
    });
  }
}
