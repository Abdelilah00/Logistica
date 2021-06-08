import {Injectable} from '@angular/core';
import {BaseService} from '../base-service.service';
import {Input} from '../../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Structure} from '../../models/Organization.model';

@Injectable({
  providedIn: 'root'
})

export class StructuresService extends BaseService<Structure, Structure> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'structures');
  }
}
