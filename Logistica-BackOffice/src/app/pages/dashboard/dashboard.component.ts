import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {latLng, tileLayer} from 'leaflet';

import {ChartType, Chat, Stat, Transaction} from './dashboard.model';

import {revenueChart, salesAnalytics, sparklineEarning, sparklineMonthly, statData, transactions} from './data';
import {DashboardService} from '../../core/services/dashboard.service';
import {Statistic} from '../../core/models/all.models';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})

/**
 * Dashboard Component
 */
export class DashboardComponent implements OnInit {
  term: any;
  chatData: Chat[];
  transactions: Transaction[];
  statData: Stat[];
  statistics = new Array<Statistic>();
  // bread crumb items
  breadCrumbItems: Array<{}>;
  revenueChart: ChartType;
  salesAnalytics: ChartType;
  sparklineEarning: ChartType;
  sparklineMonthly: ChartType;
  // Form submit
  chatSubmit: boolean;
  formData: FormGroup;
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
  }

  getHourly(): void {
    let s1 = [];
    let s2 = [];
    let s3 = [];
    this.dashboardService.getHourlyChiffreAffaire().subscribe(data => {
      for (let i = 1; i <= 24; i++) {
        const v = data[0].items.find(x => x[0] === i);
        s1.push(v === undefined ? null : v[1]);

        const vv = data[1].items.find(x => x[0] === i);
        s2.push(vv === undefined ? null : vv[1]);

        const vvv = data[2].items.find(x => x[0] === i);
        s3.push(vvv === undefined ? null : vvv[1]);
      }

      this.revenueChart.series[0].data = s1;
      this.revenueChart.series[1].data = s2;
      this.revenueChart.series[2].data = s3;
    });
  }

  getDaily(): void {
    let s1 = [];
    let s2 = [];
    let s3 = [];
    this.dashboardService.getDailyChiffreAffaire().subscribe(data => {
      for (let i = 1; i <= 31; i++) {
        const v = data[0].items.find(x => x[0] === i);
        s1.push(v === undefined ? null : v[1]);

        const vv = data[1].items.find(x => x[0] === i);
        s2.push(vv === undefined ? null : vv[1]);

        const vvv = data[2].items.find(x => x[0] === i);
        s3.push(vvv === undefined ? null : vvv[1]);
      }
      this.revenueChart.series[0].data = s1;
      this.revenueChart.series[1].data = s2;
      this.revenueChart.series[2].data = s3;
    });
  }

  getMonthly(): void {
    let s1 = [];
    let s2 = [];
    let s3 = [];
    this.dashboardService.getMonthlyChiffreAffaire().subscribe(data => {
      for (let i = 1; i <= 12; i++) {
        const v = data[0].items.find(x => x[0] === i);
        s1.push(v === undefined ? null : v[1]);

        const vv = data[1].items.find(x => x[0] === i);
        s2.push(vv === undefined ? null : vv[1]);

        const vvv = data[2].items.find(x => x[0] === i);
        s3.push(vvv === undefined ? null : vvv[1]);
      }
      this.revenueChart.series[0].data = s1;
      this.revenueChart.series[1].data = s2;
      this.revenueChart.series[2].data = s3;
    });
  }

  private resetData(): void {
    this.revenueChart.series[0].data = [];
    this.revenueChart.series[1].data = [];
    this.revenueChart.series[2].data = [];
  }


  private _fetchData() {
    this.revenueChart = revenueChart;
    this.salesAnalytics = salesAnalytics;
    this.sparklineEarning = sparklineEarning;
    this.sparklineMonthly = sparklineMonthly;
    this.transactions = transactions;
    this.statData = statData;
  }
}
