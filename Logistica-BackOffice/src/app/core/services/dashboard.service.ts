import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';
import {SeriesList, Statistic} from '../models/all.models';

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

  getMonthlyChiffreAffaire(): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getMonthlyChiffreAffaire`).pipe(retry(1));
  }

  getDailyChiffreAffaire(): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getDailyChiffreAffaire`).pipe(retry(1));
  }

  getHourlyChiffreAffaire(): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getHourlyChiffreAffaire`).pipe(retry(1));
  }
}
