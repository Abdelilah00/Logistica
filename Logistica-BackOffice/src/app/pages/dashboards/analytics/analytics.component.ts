import {Component, OnInit} from '@angular/core';
import {ChartType, Chat, Stat, Statistic, Transaction} from '../../../core/models/dashboard.model';
import {latLng, tileLayer} from 'leaflet';
import {FormBuilder} from '@angular/forms';
import {DashboardService} from '../../../core/services/dashboard.service';
import {revenueChart, salesAnalytics, sparklineEarning, sparklineMonthly, statData, transactions} from '../data';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.scss']
})

export class AnalyticsComponent implements OnInit {
  ///

  revenueChart: ChartType;
  qteChart: ChartType;
  benefitChart: ChartType;
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
  }

  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Logistica'}, {label: 'Dashboard', active: true}];
    this.dashboardService.getStatistics().subscribe(data => this.statistics = data);
    this._fetchData();
    this.getMonthly();
    this.getMonthlyQte();
    this.getMonthlyBenefits();
  }

  getHourly(): void {
    this.revenueChart = revenueChart;
    this.dashboardService.getHourlyTurnover().subscribe(data => {
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
    this.revenueChart = revenueChart;
    this.dashboardService.getDailyTurnover().subscribe(data => {
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
    this.revenueChart = revenueChart;
    this.dashboardService.getMonthlyTurnover().subscribe(data => {
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

  getMonthlyBenefits(): void {
    this.dashboardService.getMonthlyBenefits().subscribe(data => {
      for (let i = 1; i <= 12; i++) {
        const v = data[0].items.find(x => x[0] === i);
        this.benefitChart.series[0].data.push(v === undefined ? null : v[1]);

        const vv = data[1].items.find(x => x[0] === i);
        this.benefitChart.series[1].data.push(vv === undefined ? null : vv[1]);

        const vvv = data[2].items.find(x => x[0] === i);
        this.benefitChart.series[2].data.push(vvv === undefined ? null : vvv[1]);
      }
    });
  }


  private _fetchData() {
    this.revenueChart = revenueChart;
    this.qteChart = revenueChart;
    this.benefitChart = revenueChart;
    this.salesAnalytics = salesAnalytics;
    this.sparklineEarning = sparklineEarning;
    this.sparklineMonthly = sparklineMonthly;
    this.transactions = transactions;
    this.statData = statData;
  }
}
