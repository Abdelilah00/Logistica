import {Injectable} from '@angular/core';
import {BaseService} from '../base-service.service';
import {Category} from '../../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class CategoryService extends BaseService<Category, Category> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'categories');
  }

  getByParentId(id: number): Observable<Array<Category>> {
    this.loading = true;
    return this.httpClient.get<Array<Category>>(`${this.baseUrl}/getByParentId/${encodeURIComponent(String(id))}`)
      .pipe(retry(1));
  }

  getParents(): Observable<Array<Category>> {
    this.loading = true;
    return this.httpClient.get<Array<Category>>(`${this.baseUrl}/getParents`)
      .pipe(retry(1));
  }
  getChildren(): Observable<Array<Category>> {
    this.loading = true;
    return this.httpClient.get<Array<Category>>(`${this.baseUrl}/getChildren`)
      .pipe(retry(1));
  }
}
