import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {Stock} from '../models/all.models';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StocksService extends BaseService<Stock, Stock> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'stocks');
  }

}

@Injectable({
  providedIn: 'root'
})
export class StocksRespoService extends BaseService<Stock, Stock> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'stock');
  }

}
