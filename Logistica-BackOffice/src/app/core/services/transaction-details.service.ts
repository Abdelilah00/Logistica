import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {TransactionDetail} from '../models/auth.models';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransactionDetailsService extends BaseService<TransactionDetail, TransactionDetail> {
  constructor(httpClient: HttpClient) {
    super(httpClient, 'transactionDetails');
  }
}
