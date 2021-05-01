import { Injectable } from '@angular/core';
import {BaseService} from './base-service.service';
import {Input} from '../models/auth.models';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class OutputsService extends BaseService<Input, Input> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'outputs');
  }

}
