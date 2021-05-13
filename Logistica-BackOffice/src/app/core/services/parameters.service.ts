import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {Parameter} from '../models/all.models';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ParametersService extends BaseService<Parameter, Parameter> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'parameters');
  }

}
