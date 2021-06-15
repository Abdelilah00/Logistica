import {Component, OnInit, ViewChild} from '@angular/core';
import {ChartType, Chat, Stat, Statistic, Transaction} from '../../../core/models/dashboard.model';
import {latLng, tileLayer} from 'leaflet';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {DashboardService} from '../../../core/services/dashboard.service';
import {revenueChart, salesAnalytics, sparklineEarning, sparklineMonthly, statData, transactions} from '../data';
import {ChartComponent} from 'ng-apexcharts';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.scss']
})

export class AnalyticsComponent implements OnInit {
  @ViewChild('chart') chart: ChartComponent;
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  ///
  revenueChart: ChartType;
  statistics = new Array<Statistic>();
  ////

  term: any;
  chatData: Chat[];
  transactions: Transaction[];
  statData: Stat[];
  // bread crumb items
  breadCrumbItems: Array<{}>;
  salesAnalytics: ChartType;
  sparklineEarning: ChartType;
  sparklineMonthly: ChartType;
  // Form submit
  options = {
    layers: [
      tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {maxZoom: 18, attribution: '...'})
    ],
    zoom: 6,
    center: latLng(46.879966, -121.726909)
  };

  constructor(public formBuilder: FormBuilder,
              private dashboardService: DashboardService) {
    this._fetchOptions();
  }

  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Logistica'}, {label: 'Dashboard', active: true}];
    this.dashboardService.getStatistics().subscribe(data => this.statistics = data);
    this.getChart();
  }

  getChart(): void {
    const s1 = [];
    this.dashboardService.getMonthlyTurnover().subscribe(data => {
      for (let i = 1; i <= 12; i++) {
        const v = data[0].items.find(x => x[0] === i);
        s1.push(v === undefined ? null : v[1]);
      }
      this.chart.updateSeries([{
        data: s1,
        color: '#d70909',
        type: 'column',
        name: 'xxxx'
      }]);
    });
  }

  hideStat(kpi): void {
    this.statistics.find(s => s.kpi === kpi).hidden = true;
  }

  appendStatToChart(kpi): void {
    this.statistics.find(s => s.kpi === kpi).appendToChart = !this.statistics.find(s => s.kpi === kpi).appendToChart;
  }

  private _fetchOptions() {
    this.revenueChart = revenueChart;
    this.salesAnalytics = salesAnalytics;
    this.sparklineEarning = sparklineEarning;
    this.sparklineMonthly = sparklineMonthly;
    this.transactions = transactions;
    this.statData = statData;
  }
}
