import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {Stock} from '../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StocksService extends BaseService<Stock, Stock> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'stocks');
  }

  getByProductId(id: number): Observable<Array<Stock>> {
    this.loading = true;
    return this.httpClient.get<Array<Stock>>(`${this.baseUrl}/getByProductId/${encodeURIComponent(String(id))}`).pipe(retry(1));
  }
}

