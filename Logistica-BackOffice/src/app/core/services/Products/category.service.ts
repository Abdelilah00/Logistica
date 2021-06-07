import { Injectable } from '@angular/core';
import {BaseService} from '../base-service.service';
import {Category, Input} from '../../models/all.models';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class CategoryService extends BaseService<Category, Category> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'categories');
  }

}
