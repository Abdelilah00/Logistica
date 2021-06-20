import {Component, OnInit} from '@angular/core';
import {Statistic} from '../../../core/models/dashboard.model';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import * as echarts from 'echarts';
import {formatDate} from '@angular/common';
import {DashboardAnalyticsService} from '../../../core/services/Dashboards/dashboard-analytics.service';

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
  defaultTreeMap = 'products';
  ////

  // bread crumb items
  breadCrumbItems: Array<{}>;

  constructor(public formBuilder: FormBuilder,
              private dashboardAnalyticsService: DashboardAnalyticsService) {
  }


  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Logistica'}, {label: 'Dashboard', active: true}];
    this.getStatistics();
    this.getTreeMaps('products');
  }

  getStatistics(): void {
    const params = [];
    // tslint:disable-next-line:forin
    for (const key in this.range.value) {
      const date = formatDate(this.range.value[key], 'yyyy-MM-dd', 'en');
      params.push(key + '=' + date);
    }
    this.dashboardAnalyticsService.getStatistics(params).subscribe(data => {
      this.statistics = data;
      this.statistics.find(s => s.kpi === 'OUTPUT_CHIFFRE').appendToChart = true;
      this.statistics.find(s => s.kpi === 'INPUT_CHIFFRE').appendToChart = true;
      this.getChart();
    });
  }

  getChart(period: string = 'MONTH'): void {

    const params = this.statistics.filter(s => s.appendToChart === true).map(v => v.kpi);
    // tslint:disable-next-line:forin
    for (const key in this.range.value) {
      const date = formatDate(this.range.value[key], 'yyyy-MM-dd', 'en');
      params.push(key + '=' + date);
    }
    params.push('period=' + period);

    this.dashboardAnalyticsService.getPeriodicChartOf(params).subscribe(data => {
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
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
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
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: Array.from({length: series[0]?.data.length}, (v, k) => k + 1)
        },
        yAxis: {
          type: 'value',
          scale: true
        },
        series: series,

      });
    });
  }

  getTreeMaps(filter: string): void {
    const params = [];
    // tslint:disable-next-line:forin
    for (const key in this.range.value) {
      const date = formatDate(this.range.value[key], 'yyyy-MM-dd', 'en');
      params.push(key + '=' + date);
    }
    params.push('filter=' + filter);
    params.push('n=' + 10);

    this.dashboardAnalyticsService.getTreeMapOfTop(params).subscribe(data => {
      const myChart = echarts.init(document.getElementById('treeMap'));
      myChart.clear();
      myChart.setOption({
        title: {
          top: 5,
          left: 'center',
          text: 'Top 10 ' + filter,
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

  hideStat(kpi): void {
    this.statistics.find(s => s.kpi === kpi).hidden = true;
    this.statistics.find(s => s.kpi === kpi).appendToChart = false;
    this.getChart('MONTH');
  }

  appendStatToChart(kpi): void {
    this.statistics.find(s => s.kpi === kpi).appendToChart = !this.statistics.find(s => s.kpi === kpi).appendToChart;
    this.getChart('MONTH');
  }

  rangeChange(): void {
    this.getStatistics();
    this.getChart();
    this.getTreeMaps('products');
  }
}
