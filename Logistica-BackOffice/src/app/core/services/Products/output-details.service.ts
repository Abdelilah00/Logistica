import {Injectable} from '@angular/core';
import {BaseService} from '../base-service.service';
import {InputDetail, OutputDetail} from '../../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OutputDetailsService extends BaseService<OutputDetail, OutputDetail> {
  constructor(httpClient: HttpClient) {
    super(httpClient, 'outputDetails');
  }

  getByOutputId(id: number): Observable<Array<OutputDetail>> {
    this.loading = true;
    return this.httpClient.get<Array<OutputDetail>>(`${this.baseUrl}/getByOutputId/${encodeURIComponent(String(id))}`)
      .pipe(retry(1));
  }
}
