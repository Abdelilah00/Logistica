import {Injectable} from '@angular/core';
import {BaseService} from '../base-service.service';
import {Service} from '../../models/all.models';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StructureUnitsService extends BaseService<Service, Service> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'structureUnits');
  }
}
