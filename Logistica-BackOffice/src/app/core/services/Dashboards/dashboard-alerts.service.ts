import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {Observable} from 'rxjs';
import {AlertsItem} from '../../models/dashboard.model';
import {retry} from 'rxjs/operators';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DashboardAlertsService {
  protected baseUrl: string;
  protected loading: boolean;

  protected constructor(protected httpClient: HttpClient) {
    this.baseUrl = `${environment.apiBaseUrl}/dashboardAlerts`;
  }

  getRealQte(params: string[]): Observable<AlertsItem[]> {
    this.loading = true;
    const queryString = this.createQueryURL(params);
    return this.httpClient.get<AlertsItem[]>(`${this.baseUrl}/getRealQte${queryString}`).pipe(retry(1));
  }

  getPredQte(params: string[]): Observable<AlertsItem[]> {
    this.loading = true;
    const queryString = this.createQueryURL(params);
    return this.httpClient.get<AlertsItem[]>(`${this.baseUrl}/getPredQte${queryString}`).pipe(retry(1));
  }

  private createQueryURL(params: string[]): string {
    let queryString = '?';
    params.map(param => queryString += param + '&');
    return queryString.slice(0, -1);
  }
}
