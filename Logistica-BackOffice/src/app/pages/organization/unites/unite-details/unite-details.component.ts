import {Component, Input, OnInit} from '@angular/core';
import {Stock} from '../../../../core/models/all.models';
import {StocksService} from '../../../../core/services/stocks.service';


@Component({
  selector: 'app-unite-details',
  templateUrl: './unite-details.component.html',
  styleUrls: ['./unite-details.component.scss']
})

export class UniteDetailsComponent implements OnInit {
  view: Stock[];
  @Input() inputId: number | null;

  constructor(private stocksService: StocksService) {
  }

  ngOnInit(): void {
    if (this.inputId !== null) {
      this.stocksService.getByProductId(this.inputId).subscribe(data => this.view = data);
    }

  }

}
