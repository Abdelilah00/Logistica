import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {Observable} from 'rxjs';
import {SeriesList, Statistic, TreeMapItem} from '../../models/dashboard.model';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DashboardAnalyticsService {
  protected baseUrl: string;
  protected loading: boolean;

  protected constructor(protected httpClient: HttpClient) {
    this.baseUrl = `${environment.apiBaseUrl}/dashboardAnalytics`;
  }

  getStatistics(params: string[]): Observable<Array<Statistic>> {
    this.loading = true;
    return this.httpClient.get<Array<Statistic>>(`${this.baseUrl}/getStatistics`).pipe(retry(1));
  }

  getPeriodicChartOf(params: string[]): Observable<SeriesList[]> {
    this.loading = true;
    const queryString = this.createQueryURL(params);
    return this.httpClient.get<SeriesList[]>(`${this.baseUrl}/getPeriodicChart${queryString}`).pipe(retry(1));
  }

  getTreeMapOfTop(params: string[]): Observable<TreeMapItem[]> {
    this.loading = true;
    const queryString = this.createQueryURL(params);
    return this.httpClient.get<TreeMapItem[]>(`${this.baseUrl}/getTreeMapOfTop${queryString}`).pipe(retry(1));
  }

  private createQueryURL(params: string[]): string {
    let queryString = '?';
    params.map(param => queryString += param + '&');
    return queryString.slice(0, -1);
  }
}

