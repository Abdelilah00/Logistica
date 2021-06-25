import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';
import {SeriesPredList} from '../../models/dashboard.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardPredictionsService {
  protected baseUrl: string;
  protected loading: boolean;

  protected constructor(protected httpClient: HttpClient) {
    this.baseUrl = `${environment.apiBaseUrl}/dashboardPredictions`;
  }

  getChart(params: string[]): Observable<SeriesPredList> {
    this.loading = true;
    const queryString = this.createQueryURL(params);
    return this.httpClient.get<SeriesPredList>(`${this.baseUrl}/getChart${queryString}`).pipe(retry(1));
  }

  private createQueryURL(params: string[]): string {
    let queryString = '?';
    params.map(param => queryString += param + '&');
    return queryString.slice(0, -1);
  }
}
