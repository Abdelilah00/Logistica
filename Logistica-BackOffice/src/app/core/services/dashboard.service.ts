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

  getMonthlyTurnover(): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getMonthlyTurnover`).pipe(retry(1));
  }


  getDailyTurnover(): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getDailyTurnover`).pipe(retry(1));
  }

  getHourlyTurnover(): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getHourlyTurnover`).pipe(retry(1));
  }

  getMonthlyQte(): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getMonthlyQte`).pipe(retry(1));
  }

  getMonthlyBenefits(): Observable<Array<SeriesList>> {
    this.loading = true;
    return this.httpClient.get<Array<SeriesList>>(`${this.baseUrl}/getMonthlyBenefits`).pipe(retry(1));
  }

}
