import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {Product} from '../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IListModel} from '../models/base-model.model';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductsService extends BaseService<Product, Product> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'products');
  }

  getByStockId(id: number): Observable<Array<Product>> {
    this.loading = true;
    return this.httpClient.get<Array<Product>>(`${this.baseUrl}/getByStockId/${encodeURIComponent(String(id))}`).pipe(retry(1));
  }
}
