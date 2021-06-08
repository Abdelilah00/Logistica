import {Component, Input, OnInit} from '@angular/core';
import {Statistic} from '../../../core/models/all.models';

@Component({
  selector: 'app-stat',
  templateUrl: './stat.component.html',
  styleUrls: ['./stat.component.scss']
})
export class StatComponent implements OnInit {

  @Input() title: string;
  @Input() data: Statistic;
  @Input() icon: string;

  constructor() {
  }

  ngOnInit(): void {
  }

}
