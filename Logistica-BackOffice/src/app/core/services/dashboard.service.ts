import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  protected baseUrl: string;
  protected loading: boolean;


  protected constructor(protected httpClient: HttpClient) {
    this.baseUrl = `${environment.apiBaseUrl}/dashboard`;
  }

  getTotaleInput(): Observable<number> {
    this.loading = true;
    return this.httpClient.get<number>(`${this.baseUrl}/getTotaleInput`).pipe(retry(1));
  }

  getTotaleOutput(): Observable<number> {
    this.loading = true;
    return this.httpClient.get<number>(`${this.baseUrl}/getTotaleOutput`).pipe(retry(1));
  }

  getTotaleTransfer(): Observable<number> {
    this.loading = true;
    return this.httpClient.get<number>(`${this.baseUrl}/getTotaleTransfer`).pipe(retry(1));
  }

  getMonthlyChiffreAffaire(): Observable<Array<number>> {
    this.loading = true;
    return this.httpClient.get<Array<number>>(`${this.baseUrl}/getMonthlyChiffreAffaire`).pipe(retry(1));
  }
}
