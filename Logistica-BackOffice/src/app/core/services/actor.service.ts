import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {Supplier} from '../models/all.models';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ActorService extends BaseService<Supplier, Supplier> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'actors');
  }

  // todo:getSuppliers
  // todo:getClients
  // todo:getResponsible
}
