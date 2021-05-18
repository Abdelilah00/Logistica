import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {TransactionDetail} from '../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TransactionDetailsService extends BaseService<TransactionDetail, TransactionDetail> {
  constructor(httpClient: HttpClient) {
    super(httpClient, 'transactionDetails');
  }

  getByInputId(id: number): Observable<Array<TransactionDetail>> {
    this.loading = true;
    return this.httpClient.get<Array<TransactionDetail>>(`${this.baseUrl}/getByInputId/${encodeURIComponent(String(id))}`)
      .pipe(retry(1));
  }

  getByOutputId(id: number): Observable<Array<TransactionDetail>> {
    this.loading = true;
    return this.httpClient.get<Array<TransactionDetail>>(`${this.baseUrl}/getByOutputId/${encodeURIComponent(String(id))}`)
      .pipe(retry(1));
  }
}
