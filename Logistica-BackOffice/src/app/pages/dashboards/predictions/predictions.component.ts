import {Component, OnInit} from '@angular/core';
import {DashboardPredictionsService} from '../../../core/services/Dashboards/dashboard-predictions.service';
import * as echarts from 'echarts';

@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.scss']
})
export class PredictionsComponent implements OnInit {

  constructor(private dashboardPredictionsService: DashboardPredictionsService) {
  }

  ngOnInit(): void {
    let base = +new Date(1988, 9, 3);
    const oneDay = 24 * 3600 * 1000;
    const data = [[base, Math.random() * 300]];

    for (let i = 1; i < 20000; i++) {
      const now = new Date(base += oneDay);
      // @ts-ignore
      data.push([[now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
        Math.round((Math.random() - 0.5) * 20 + data[i - 1][1])
      ]);
    }
    const myChart = echarts.init(document.getElementById('chart'));
    myChart.clear();
    console.log(data);
    myChart.setOption({
      tooltip: {
        trigger: 'axis',
        position: (pt) => [pt[0], '10%']
      },
      title: {
        left: 'center',
        text: 'Sells Qte',
      },
      toolbox: {
        feature: {
          dataZoom: {
            yAxisIndex: 'none'
          },
          restore: {},
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'time',
        boundaryGap: false
      },
      yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
      },
      dataZoom: [{
        type: 'inside',
        start: 0,
        end: 20
      }, {
        start: 0,
        end: 20
      }],
      series: [
        {
          name: '模拟数据',
          type: 'line',
          smooth: true,
          symbol: 'none',
          data: data
        }
      ]
    });
    this.getChart(['filter=products']);
  }

  getChart(params: string[]): void {
    this.dashboardPredictionsService.getChart(params).subscribe(data => {
      const myChart = echarts.init(document.getElementById('chart'));
      myChart.clear();
      myChart.setOption({
        tooltip: {
          trigger: 'axis',
          position: function(pt) {
            return [pt[0], '10%'];
          }
        },
        title: {
          left: 'center',
          text: '大数据量面积图',
        },
        toolbox: {
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'time',
          boundaryGap: false
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '100%']
        },
        dataZoom: [{
          type: 'inside',
          start: 0,
          end: 20
        }, {
          start: 0,
          end: 20
        }],
        series: [
          {
            name: '模拟数据',
            type: 'line',
            smooth: true,
            symbol: 'none',
            areaStyle: {},
            data: data
          }
        ]
      });
    });
  }
}
