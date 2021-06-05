import {Injectable} from '@angular/core';
import {BaseService} from './base-service.service';
import {Actor} from '../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ActorService extends BaseService<Actor, Actor> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'actors');
  }

  getSuppliers(skipCount?: number | null | undefined, maxResultCount?: number | null | undefined): Observable<Array<Actor>> {
    this.loading = true;
    return this.httpClient.get<Array<Actor>>(this.baseUrl + '/getSuppliers')
      .pipe(retry(1));
  }

  getClients(skipCount?: number | null | undefined, maxResultCount?: number | null | undefined): Observable<Array<Actor>> {
    this.loading = true;
    return this.httpClient.get<Array<Actor>>(this.baseUrl + '/getClients')
      .pipe(retry(1));
  }

  getResponsible(skipCount?: number | null | undefined, maxResultCount?: number | null | undefined): Observable<Array<Actor>> {
    this.loading = true;
    return this.httpClient.get<Array<Actor>>(this.baseUrl + '/getResponsible')
      .pipe(retry(1));
  }
}


