import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {Product} from '../models/all.models';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductsService extends BaseService<Product, Product> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'products');
  }

}
