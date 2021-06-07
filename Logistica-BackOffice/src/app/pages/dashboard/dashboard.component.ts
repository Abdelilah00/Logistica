import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {latLng, tileLayer} from 'leaflet';

import {ChartType, Chat, Stat, Transaction} from './dashboard.model';

import {chatData, revenueChart, salesAnalytics, sparklineEarning, sparklineMonthly, statData, transactions} from './data';
import {DashboardService} from '../../core/services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})

/**
 * Dashboard Component
 */
export class DashboardComponent implements OnInit {
  inputCount: number;
  outputCount: number;
  transferCount: number;
  /////////////////////
  term: any;
  chatData: Chat[];
  transactions: Transaction[];
  statData: Stat[];
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

  constructor(public formBuilder: FormBuilder, private dashboardService: DashboardService) {
  }

  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Logistica'}, {label: 'Dashboard', active: true}];
    this.dashboardService.getTotaleInput().subscribe(data => this.inputCount = data);
    this.dashboardService.getTotaleOutput().subscribe(data => this.outputCount = data);
    this.dashboardService.getTotaleTransfer().subscribe(data => this.transferCount = data);
    this._fetchData();
    this.dashboardService.getMonthlyChiffreAffaire().subscribe(data => this.revenueChart.series[0].data = data);
  }


  private _fetchData() {
    this.revenueChart = revenueChart;
    this.salesAnalytics = salesAnalytics;
    this.sparklineEarning = sparklineEarning;
    this.sparklineMonthly = sparklineMonthly;
    this.chatData = chatData;
    this.transactions = transactions;
    this.statData = statData;
  }
}
