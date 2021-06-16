import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';
import {SeriesList, Statistic} from '../models/dashboard.model';

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

  getMonthly(chartsOf: string[]): Observable<Array<SeriesList>> {
    let queryString = '?period=month&';
    chartsOf.map(param => queryString += param + '&');
    queryString = queryString.slice(0, -1);

    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getChart${queryString}`).pipe(retry(1));
  }


  getDaily(chartsOf: string[]): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getChart`).pipe(retry(1));
  }

  getHourly(chartsOf: string[]): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getChart`).pipe(retry(1));
  }

}
