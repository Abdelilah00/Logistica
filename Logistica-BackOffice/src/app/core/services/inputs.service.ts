import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {HttpClient} from '@angular/common/http';
import {Input} from '../models/all.models';

@Injectable({
  providedIn: 'root'
})
export class InputsService extends BaseService<Input, Input> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'inputs');
  }

}
