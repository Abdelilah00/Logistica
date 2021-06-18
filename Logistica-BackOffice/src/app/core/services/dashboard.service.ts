import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';
import {SeriesList, Statistic, TreeMapItem} from '../models/dashboard.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  protected baseUrl: string;
  protected loading: boolean;


  protected constructor(protected httpClient: HttpClient) {
    this.baseUrl = `${environment.apiBaseUrl}/dashboard`;
  }

  getStatistics(): Observable<Array<Statistic>> {
    this.loading = true;
    return this.httpClient.get<Array<Statistic>>(`${this.baseUrl}/getStatistics`).pipe(retry(1));
  }

  getPeriodicChartOf(params: string[]): Observable<SeriesList[]> {
    this.loading = true;
    let queryString = '?';
    params.map(param => queryString += param + '&');
    queryString = queryString.slice(0, -1);
    return this.httpClient.get<SeriesList[]>(`${this.baseUrl}/getPeriodicChart${queryString}`).pipe(retry(1));
  }

  getTreeMapOfTopProducts(params: string[]): Observable<TreeMapItem[]> {
    this.loading = true;
    let queryString = '?';
    params.map(param => queryString += param + '&');
    queryString = queryString.slice(0, -1);
    return this.httpClient.get<TreeMapItem[]>(`${this.baseUrl}/getTreeMapOfTopProducts${queryString}`).pipe(retry(1));
  }
  getTreeMapOfTopClient(params: string[]): Observable<TreeMapItem[]> {
    this.loading = true;
    let queryString = '?';
    params.map(param => queryString += param + '&');
    queryString = queryString.slice(0, -1);
    return this.httpClient.get<TreeMapItem[]>(`${this.baseUrl}/getTreeMapOfTopClient${queryString}`).pipe(retry(1));
  }
}
