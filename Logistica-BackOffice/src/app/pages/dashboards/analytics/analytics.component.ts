import {Component, OnInit} from '@angular/core';
import {Statistic, Transaction} from '../../../core/models/dashboard.model';
import {latLng, tileLayer} from 'leaflet';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {DashboardService} from '../../../core/services/dashboard.service';
import * as echarts from 'echarts';
import {EChartsOption} from 'echarts';
import {formatDate} from '@angular/common';

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
  statistics = new Array<Statistic>();
  ////

  transactions: Transaction[];
  // bread crumb items
  breadCrumbItems: Array<{}>;

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
  }


  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Logistica'}, {label: 'Dashboard', active: true}];
    this.getStatistics();
    this.getChart();
    this.getTreeMaps();
  }

  getStatistics(): void {
    const params = [];
    // tslint:disable-next-line:forin
    for (const key in this.range.value) {
      const date = formatDate(this.range.value[key], 'yyyy-MM-dd', 'en');
      params.push(key + '=' + date);
    }
    this.dashboardService.getStatistics(params).subscribe(data => {
      this.statistics = data;
    });
  }

  getTreeMaps(): void {
    const params = [];
    // tslint:disable-next-line:forin
    for (const key in this.range.value) {
      const date = formatDate(this.range.value[key], 'yyyy-MM-dd', 'en');
      params.push(key + '=' + date);
    }
    this.dashboardService.getTreeMapOfTopProducts(params).subscribe(data => {
      const myChart = echarts.init(document.getElementById('treeMap0'));
      myChart.clear();
      myChart.setOption({
        title: {
          top: 5,
          left: 'center',
          text: 'Top 10 Products',
        },
        legend: {
          selectedMode: 'single',
          top: 55,
          itemGap: 5,
          borderRadius: 5
        },

        tooltip: {},

        series: [{
          type: 'treemap',
          data: data
        }]
      });

    });
    this.dashboardService.getTreeMapOfTopClient(params).subscribe(data => {
      const myChart = echarts.init(document.getElementById('treeMap1'));
      myChart.clear();
      myChart.setOption({
        title: {
          top: 5,
          left: 'center',
          text: 'Top 10 Clients',
        },
        legend: {
          selectedMode: 'single',
          top: 55,
          itemGap: 5,
          borderRadius: 5
        },

        tooltip: {},

        series: [{
          type: 'treemap',
          data: data
        }]
      });

    });
  }


  getChart(period: string = 'MONTH'): void {
    const params = this.statistics.filter(s => s.appendToChart === true).map(v => v.kpi);
    // tslint:disable-next-line:forin
    for (const key in this.range.value) {
      const date = formatDate(this.range.value[key], 'yyyy-MM-dd', 'en');
      params.push(key + '=' + date);
    }

    console.log(params);
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
          data: Array.from({length: series[0]?.data.length}, (v, k) => k + 1)
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

  rangeChange(): void {
    this.getStatistics();
    this.getChart();
    this.getTreeMaps();
  }

  appendStatToChart(kpi): void {
    this.statistics.find(s => s.kpi === kpi).appendToChart = !this.statistics.find(s => s.kpi === kpi).appendToChart;
    this.getChart('MONTH');
  }
}
