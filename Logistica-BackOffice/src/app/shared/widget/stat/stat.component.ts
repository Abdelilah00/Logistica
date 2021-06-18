import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Statistic} from '../../../core/models/dashboard.model';

@Component({
  selector: 'app-stat',
  templateUrl: './stat.component.html',
  styleUrls: ['./stat.component.scss']
})
export class StatComponent implements OnInit {

  @Input() data: Statistic;
  @Output() appendStatToChart = new EventEmitter<any>();
  @Output() hideStat = new EventEmitter<any>();
  @ViewChild('stat', {static: true}) stat: ElementRef<HTMLInputElement>;

  constructor() {
  }

  ngOnInit(): void {
    this.data.value = Number((this.data.value).toFixed(2));
  }

  appendStatToChartClick(kpi): void {
    this.appendStatToChart.emit(kpi);
  }

  hideStatClick(kpi): void {
    this.hideStat.emit(kpi);
  }
}
