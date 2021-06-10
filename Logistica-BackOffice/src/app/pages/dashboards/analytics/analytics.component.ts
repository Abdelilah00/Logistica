import {Component, OnInit} from '@angular/core';
import {ChartType, Chat, Stat, Statistic, Transaction} from '../../../core/models/dashboard.model';
import {latLng, tileLayer} from 'leaflet';
import {FormBuilder} from '@angular/forms';
import {DashboardService} from '../../../core/services/dashboard.service';
import {qteChart, revenueChart, salesAnalytics, sparklineEarning, sparklineMonthly, statData, transactions} from '../data';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.scss']
})

export class AnalyticsComponent implements OnInit {
  term: any;
  chatData: Chat[];
  transactions: Transaction[];
  statData: Stat[];
  statistics = new Array<Statistic>();
  // bread crumb items
  breadCrumbItems: Array<{}>;
  revenueChart: ChartType;
  qteChart: ChartType;
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
  }

  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Logistica'}, {label: 'Dashboard', active: true}];
    this.dashboardService.getStatistics().subscribe(data => this.statistics = data);
    this._fetchData();
    this.getMonthly();
    this.getMonthlyQte();
  }

  getHourly(): void {
    this.resetData();
    this.dashboardService.getHourlyChiffreAffaire().subscribe(data => {
      for (let i = 1; i <= 24; i++) {
        const v = data[0].items.find(x => x[0] === i);
        this.revenueChart.series[0].data.push(v === undefined ? null : v[1]);

        const vv = data[1].items.find(x => x[0] === i);
        this.revenueChart.series[1].data.push(vv === undefined ? null : vv[1]);

        const vvv = data[2].items.find(x => x[0] === i);
        this.revenueChart.series[2].data.push(vvv === undefined ? null : vvv[1]);
      }
    });
  }

  getDaily(): void {
    this.resetData();
    this.dashboardService.getDailyChiffreAffaire().subscribe(data => {
      for (let i = 1; i <= 31; i++) {
        const v = data[0].items.find(x => x[0] === i);
        this.revenueChart.series[0].data.push(v === undefined ? null : v[1]);

        const vv = data[1].items.find(x => x[0] === i);
        this.revenueChart.series[1].data.push(vv === undefined ? null : vv[1]);

        const vvv = data[2].items.find(x => x[0] === i);
        this.revenueChart.series[2].data.push(vvv === undefined ? null : vvv[1]);
      }
    });
  }

  getMonthly(): void {
    this.resetData();
    this.dashboardService.getMonthlyChiffreAffaire().subscribe(data => {
      for (let i = 1; i <= 12; i++) {
        const v = data[0].items.find(x => x[0] === i);
        this.revenueChart.series[0].data.push(v === undefined ? null : v[1]);

        const vv = data[1].items.find(x => x[0] === i);
        this.revenueChart.series[1].data.push(vv === undefined ? null : vv[1]);

        const vvv = data[2].items.find(x => x[0] === i);
        this.revenueChart.series[2].data.push(vvv === undefined ? null : vvv[1]);
      }
    });
  }

  getMonthlyQte(): void {
    this.resetData();
    this.dashboardService.getMonthlyQte().subscribe(data => {
      for (let i = 1; i <= 12; i++) {
        const v = data[0].items.find(x => x[0] === i);
        this.qteChart.series[0].data.push(v === undefined ? null : v[1]);

        const vv = data[1].items.find(x => x[0] === i);
        this.qteChart.series[1].data.push(vv === undefined ? null : vv[1]);

        const vvv = data[2].items.find(x => x[0] === i);
        this.qteChart.series[2].data.push(vvv === undefined ? null : vvv[1]);
      }
    });
  }

  private resetData(): void {
    this.revenueChart.series[0].data = [];
    this.revenueChart.series[1].data = [];
    this.revenueChart.series[2].data = [];
  }


  private _fetchData() {
    this.revenueChart = revenueChart;
    this.qteChart = qteChart;
    this.salesAnalytics = salesAnalytics;
    this.sparklineEarning = sparklineEarning;
    this.sparklineMonthly = sparklineMonthly;
    this.transactions = transactions;
    this.statData = statData;
  }
}
