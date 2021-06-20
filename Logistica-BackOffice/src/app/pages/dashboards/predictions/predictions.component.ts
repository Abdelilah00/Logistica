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
    this.getChart(['filter=products']);
  }

  getChart(params: string[]): void {
    this.dashboardPredictionsService.getChart(params).subscribe(data => {
      const myChart = echarts.init(document.getElementById('chart'));
      myChart.clear();
      const finalData = [[]];
      data.items.map(obj => finalData.push([obj.time, obj.value]));

      myChart.setOption({
        tooltip: {
          trigger: 'axis',
          position: function(pt) {
            return [pt[0], '10%'];
          }
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
            smooth: false,
            symbol: 'none',
            data: finalData,
            markLine: {

              silent: true,
              data: [
                {
                  yAxis: data.max,
                  lineStyle: {
                    normal: {
                      color: '#0af34c',
                    }
                  }
                },
                {
                  yAxis: data.med,
                  lineStyle: {
                    normal: {
                      color: '#0a7bf3',
                    }
                  }
                },
                {
                  yAxis: data.min,
                  lineStyle: {
                    normal: {
                      color: '#f30a12',
                    }
                  }
                }]
            }
          }
        ],
      });
    });
  }
}
