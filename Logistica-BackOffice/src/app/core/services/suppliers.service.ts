import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {Supplier} from '../models/auth.models';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SuppliersService extends BaseService<Supplier, Supplier> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'suppliers');
  }

}
