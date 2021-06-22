import {Component, OnInit} from '@angular/core';
import {DashboardPredictionsService} from '../../../core/services/Dashboards/dashboard-predictions.service';
import * as echarts from 'echarts';
import {ProductsService} from '../../../core/services/Products/products.service';
import {Product} from '../../../core/models/all.models';

@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.scss']
})
export class PredictionsComponent implements OnInit {
  defaultProduct = 1;
  products: Product[];

  constructor(private dashboardPredictionsService: DashboardPredictionsService, private productsService: ProductsService) {
  }

  ngOnInit(): void {
    this.getChart();
    this.productsService.getAll().subscribe(data => this.products = data);
  }

  getChart(): void {
    const pId = ['productId=' + this.defaultProduct];
    this.dashboardPredictionsService.getChart(pId).subscribe(data => {
      const myChart = echarts.init(document.getElementById('chart'));
      myChart.clear();
      const finalRealData = [[]];
      const finalPredData = [[]];
      data.items.map(obj => finalRealData.push([obj.time, obj.value]));
      data.predItems.map(obj => finalPredData.push([obj.time, obj.value]));

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
            name: 'Real',
            type: 'line',
            smooth: false,
            symbol: 'none',
            data: finalRealData,
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
          }, {
            name: 'Predicted',
            type: 'line',
            smooth: false,
            symbol: 'none',
            data: finalPredData
          }
        ],
      });
    });
  }
}
