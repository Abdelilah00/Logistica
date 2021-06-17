import {Component, OnInit} from '@angular/core';
import {ChartType, Chat, Stat, Statistic, Transaction} from '../../../core/models/dashboard.model';
import {latLng, tileLayer} from 'leaflet';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {DashboardService} from '../../../core/services/dashboard.service';
import {revenueChart, salesAnalytics, sparklineEarning, sparklineMonthly, statData, transactions} from '../data';
import * as echarts from 'echarts';
import {EChartsOption} from 'echarts';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.scss']
})

export class AnalyticsComponent implements OnInit {
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
  chartOption: EChartsOption;

  constructor(public formBuilder: FormBuilder,
              private dashboardService: DashboardService) {
    this._fetchOptions();
  }

  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Logistica'}, {label: 'Dashboard', active: true}];
    this.dashboardService.getStatistics().subscribe(data => {
      this.statistics = data;
      this.statistics.find(s => s.kpi === 'INPUT_CHIFFRE').appendToChart = true;
      this.statistics.find(s => s.kpi === 'OUTPUT_CHIFFRE').appendToChart = true;
      this.getChart('MONTH');
    });

  }


  getChart(period: string): void {
    const params = this.statistics.filter(s => s.appendToChart === true).map(v => v.kpi);
    params.push('period=' + period);
    this.dashboardService.getPeriodicChartOf(params).subscribe(data => {
      const series = [];
      for (const d of data) {
        const s = [];
        const iMax = period === 'HOUR' ? 24 : period === 'DAY' ? 31 : 12;
        for (let i = 1; i <= iMax; i++) {
          const v = d.items.find(x => x.time === i);
          s.push(v === undefined ? 0 : v.value);
        }
        series.push({
          name: d.kpi,
          type: 'line',
          data: s,
          stack: d.kpi,
        });
      }
      console.log(series);
      const myChart = echarts.init(document.getElementById('chart'));
      myChart.clear();
      myChart.setOption({
        title: {
          text: 'Periodic Analytics'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        legend: {
          show: true,
          right: '10%',
          top: 25,
          itemWidth: 15,
          itemHeight: 10,
          textStyle: {
            color: '#1a1a1a',
            fontSize: 12,
          },
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: Array.from({length: series[0].data.length}, (v, k) => k + 1)
        },
        yAxis: {
          type: 'value',
          scale: true
        },
        series: series
      });
    });
  }

  hideStat(kpi): void {
    this.statistics.find(s => s.kpi === kpi).hidden = true;
    this.statistics.find(s => s.kpi === kpi).appendToChart = false;
    this.getChart('MONTH');
  }

  appendStatToChart(kpi): void {
    this.statistics.find(s => s.kpi === kpi).appendToChart = !this.statistics.find(s => s.kpi === kpi).appendToChart;
    this.getChart('MONTH');
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
